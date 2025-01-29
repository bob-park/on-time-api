package com.malgn.ontimeapi.domain.user.model.v1;

import com.malgn.ontimeapi.domain.user.model.AddNotificationProviderRequest;

public record AddNotificationProviderV1Request(Long providerId)
    implements AddNotificationProviderRequest {
}
