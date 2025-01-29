package com.malgn.ontimeapi.domain.position.model.v1;

import lombok.Builder;

import com.malgn.ontimeapi.domain.position.model.CreatePositionRequest;

@Builder
public record CreatePositionV1Request(String name,
                                      String description)
    implements CreatePositionRequest {
}
