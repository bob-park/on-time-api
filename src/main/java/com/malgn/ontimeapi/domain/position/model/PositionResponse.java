package com.malgn.ontimeapi.domain.position.model;

import com.malgn.common.model.CommonResponse;

public interface PositionResponse extends CommonResponse {
    Long id();

    String name();
}
