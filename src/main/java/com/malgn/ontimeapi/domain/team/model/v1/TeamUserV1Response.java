package com.malgn.ontimeapi.domain.team.model.v1;

import lombok.Builder;

import com.malgn.ontimeapi.domain.team.entity.TeamUser;
import com.malgn.ontimeapi.domain.team.model.TeamUserResponse;

@Builder
public record TeamUserV1Response(String userUniqueId,
                                 boolean isLeader)
    implements TeamUserResponse {

    public static TeamUserResponse from(TeamUser teamUser) {
        return TeamUserV1Response.builder()
            .userUniqueId(teamUser.getUserUniqueId())
            .isLeader(teamUser.isLeader())
            .build();
    }
}
