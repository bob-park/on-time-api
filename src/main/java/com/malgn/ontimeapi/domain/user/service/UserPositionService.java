package com.malgn.ontimeapi.domain.user.service;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.user.entity.UserPosition;
import com.malgn.ontimeapi.domain.user.model.UserPositionResponse;

public interface UserPositionService {

    UserPositionResponse getUserPosition(Id<UserPosition, String> userId);

}
