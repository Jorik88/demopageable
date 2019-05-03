package com.example.demopageable.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.configuration.authservice")
public class AuthConfiguration {

    private String host;
    private String authenticationApiKey;
    private String usersApiKey;
    private String adminApiKey;
}
