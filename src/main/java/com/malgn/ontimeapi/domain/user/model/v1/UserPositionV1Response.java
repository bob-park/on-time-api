package com.malgn.ontimeapi.domain.user.model.v1;

import lombok.Builder;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.position.entity.Position;
import com.malgn.ontimeapi.domain.position.model.PositionResponse;
import com.malgn.ontimeapi.domain.position.model.v1.PositionV1Response;
import com.malgn.ontimeapi.domain.user.entity.UserPosition;
import com.malgn.ontimeapi.domain.user.model.UserPositionResponse;

@Builder
public record UserPositionV1Response(String userUniqueId,
                                     PositionResponse position)
    implements UserPositionResponse {

    public static UserPositionResponse from(String userUniqueId, Position position) {
        return UserPositionV1Response.builder()
            .userUniqueId(userUniqueId)
            .position(PositionV1Response.from(position))
            .build();
    }

}
