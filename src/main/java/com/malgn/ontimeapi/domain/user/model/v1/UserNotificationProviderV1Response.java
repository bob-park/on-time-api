package com.malgn.ontimeapi.domain.user.model.v1;

import lombok.Builder;

import com.malgn.ontimeapi.domain.user.entity.UserNotificationProvider;
import com.malgn.ontimeapi.domain.user.model.UserNotificationProviderResponse;

@Builder
public record UserNotificationProviderV1Response(Long id,
                                                 String userUniqueId,
                                                 Long providerId)
    implements UserNotificationProviderResponse {

    public static UserNotificationProviderResponse from(UserNotificationProvider userNotificationProvider) {
        return UserNotificationProviderV1Response.builder()
            .id(userNotificationProvider.getId())
            .userUniqueId(userNotificationProvider.getUserUniqueId())
            .providerId(userNotificationProvider.getProviderId())
            .build();
    }
}
