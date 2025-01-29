package com.malgn.ontimeapi.domain.user.repository.query;

import java.util.Optional;

import com.malgn.ontimeapi.domain.user.entity.UserNotificationProvider;

public interface UserNotificationProviderQueryRepository {

    Optional<UserNotificationProvider> getProvider(String userUniqueId);

    void deleteProvider(String userUniqueId);

}
