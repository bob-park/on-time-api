package com.malgn.ontimeapi.domain.user.model.v1;

import com.malgn.ontimeapi.domain.user.model.UpdateUserPositionRequest;

public record UpdateUserPositionV1Request(Long positionId)
    implements UpdateUserPositionRequest {
}
