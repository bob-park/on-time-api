package com.malgn.ontimeapi.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.user.entity.UserNotificationProvider;
import com.malgn.ontimeapi.domain.user.repository.query.UserNotificationProviderQueryRepository;

public interface UserNotificationProviderRepository extends JpaRepository<UserNotificationProvider, Long>,
    UserNotificationProviderQueryRepository {
}
