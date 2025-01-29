package com.malgn.ontimeapi.domain.user.service;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.user.model.AddNotificationProviderRequest;
import com.malgn.ontimeapi.domain.user.model.UserNotificationProviderResponse;
import com.malgn.ontimeapi.domain.user.model.UserResponse;

public interface UserNotificationProviderService {

    UserNotificationProviderResponse addProvider(Id<UserResponse, String> userUnqiueId, AddNotificationProviderRequest addRequest);

    UserNotificationProviderResponse removeProvider(Id<UserResponse, String> userUnqiueId);

}
