package com.example.demopageable.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class User {
    private Long id;
    private String email;
    private String phone;
    private boolean blocked;
    private boolean activated;
    private boolean questionnaireCompleted;
    private boolean verificationOfflineCompleted;
    private boolean verificationOnlineCompleted;
    private List<String> tags;
    private Long freshdeskId;
    private String language;
    private Long referrerId;

}
