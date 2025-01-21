package com.malgn.ontimeapi.domain.user.model;

import com.malgn.ontimeapi.domain.position.model.PositionResponse;

public interface UserPositionResponse {

    String userUniqueId();

    PositionResponse position();

}
