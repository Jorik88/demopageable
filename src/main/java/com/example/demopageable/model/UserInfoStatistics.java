package com.example.demopageable.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoStatistics {

    private Long totalRegistered;
    private Long totalActiveUsers;
    private Long totalPartners;
    private Long verifiedUsersFirstLevel;
    private Long verifiedUsersSecondLevel;

}
