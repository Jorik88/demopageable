package com.example.demopageable.service;

import com.example.demopageable.configuration.AuthConfiguration;
import com.example.demopageable.model.BaseDataResponse;
import com.example.demopageable.model.PageImpl;
import com.example.demopageable.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Service
public class UserInfoService {

    private static final String API_KEY_HEADER = "x-api-key";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    private final AuthConfiguration configuration;


    @Autowired
    public UserInfoService(AuthConfiguration configuration) {
        this.configuration = configuration;
    }

    public PageImpl<User> getUserData(Integer page, Integer size) {
        URI targetUrl= UriComponentsBuilder.fromUriString(configuration.getHost())
                .path("/api/v1/users")
                .queryParam("page", page)
                .queryParam("size", size)
                .build()
                .toUri();
        HttpEntity httpEntity = getHttpEntity(configuration.getAdminApiKey());
        ResponseEntity<BaseDataResponse<PageImpl<User>>> exchange = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<BaseDataResponse<PageImpl<User>>>() {
        });
        BaseDataResponse<PageImpl<User>> body = exchange.getBody();
        return body.getData();
    }

    private HttpEntity getHttpEntity(String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_KEY_HEADER, apiKey);
        return new HttpEntity<>(headers);
    }

    public Long test() {
        Long totalUsers = 0L;
        Integer page = 0;
        Integer size = 2;
        while(true) {
            PageImpl<User> userData = getUserData(page, size);
            List<User> content = userData.getContent();
            if (content.size() == 0) {
                break;
            }
//            System.out.println(content);
            page++;
            totalUsers+= content.size();
        }
        return totalUsers;
    }
}
