package com.malgn.ontimeapi.domain.position.model;

import com.malgn.ontimeapi.common.result.CommonResponse;

public interface PositionResponse extends CommonResponse {
    Long id();

    String name();
}
