package com.malgn.ontimeapi.domain.user.service;

import com.malgn.ontimeapi.domain.user.model.UpdateUserPositionRequest;
import com.malgn.ontimeapi.domain.user.model.UserPositionResponse;

public interface UserPositionService {

    UserPositionResponse updatePosition(String uniqueUserId, UpdateUserPositionRequest updateRequest);

    UserPositionResponse getUserPosition(String uniqueUserId);

}
