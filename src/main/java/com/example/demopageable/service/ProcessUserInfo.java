package com.example.demopageable.service;

import com.example.demopageable.model.PageImpl;
import com.example.demopageable.model.User;
import com.example.demopageable.model.UserInfoStatistics;
import com.example.demopageable.repository.PurchaseOrderStatisticRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ProcessUserInfo implements IProcessUserInfo {

    private final UserInfoService usersInfoService;
    private final PurchaseOrderStatisticRepository orderStatisticRepository;

    @Autowired
    public ProcessUserInfo(UserInfoService usersInfoService, PurchaseOrderStatisticRepository orderStatisticRepository) {
        this.usersInfoService = usersInfoService;
        this.orderStatisticRepository = orderStatisticRepository;
    }

    @Override
    public UserInfoStatistics processUserInfoData() {
        Integer page = 0;
        Integer size = 2;
        Long totalUsers = 0L;
        Long totalActiveUsers = 0L;
        Set<Long> uniqRefererId = new HashSet<>();
        Long verifiedUsersFirstLevel = 0L;
        Long verifiedUsersSecondLevel = 0L;

        while (true) {
            PageImpl<User> userData = usersInfoService.getUserData(page, size);
            List<User> users = userData.getContent();
            if (users.size() == 0) {
                break;
            }
            for (User user : users) {
                totalActiveUsers = countRegisteredUsers(totalActiveUsers, user);
                countRefererId(uniqRefererId, user);
                verifiedUsersSecondLevel = countLevelVerification(verifiedUsersSecondLevel, user, user.isVerificationOfflineCompleted());
                verifiedUsersFirstLevel = countLevelVerification(verifiedUsersFirstLevel, user, !user.isVerificationOnlineCompleted());
            }

            page++;
            totalUsers += users.size();
        }

        return initUserInfoStatistics(totalUsers, totalActiveUsers, uniqRefererId, verifiedUsersFirstLevel, verifiedUsersSecondLevel);
    }

    private UserInfoStatistics initUserInfoStatistics(Long totalUsers, Long totalActiveUsers, Set<Long> uniqRefererId, Long verifiedUsersFirstLevel, Long verifiedUsersSecondLevel) {
        UserInfoStatistics userInfoStatistics = new UserInfoStatistics();
        userInfoStatistics.setTotalRegistered(totalUsers);
        userInfoStatistics.setTotalActiveUsers(totalActiveUsers);
        userInfoStatistics.setTotalPartners((long) uniqRefererId.size());
        userInfoStatistics.setVerifiedUsersFirstLevel(verifiedUsersFirstLevel);
        userInfoStatistics.setVerifiedUsersSecondLevel(verifiedUsersSecondLevel);
        return userInfoStatistics;
    }

    private Long countLevelVerification(Long verifiedUsersLevel, User user, boolean verificationOfflineCompleted) {
        if (user.isQuestionnaireCompleted() && verificationOfflineCompleted) {
            verifiedUsersLevel++;
        }
        return verifiedUsersLevel;
    }

    private Long countRegisteredUsers(Long totalActiveUsers, User user) {
        if (orderStatisticRepository.existsByUserId(user.getId())) {
            totalActiveUsers++;
        }
        return totalActiveUsers;
    }

    private void countRefererId(Set<Long> uniqRefererId, User user) {
        if (user.getReferrerId() != null) {
            uniqRefererId.add(user.getReferrerId());
        }
    }
}
