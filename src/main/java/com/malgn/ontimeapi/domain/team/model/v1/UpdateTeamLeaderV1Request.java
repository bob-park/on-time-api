package com.malgn.ontimeapi.domain.team.model.v1;

import lombok.Builder;

import com.malgn.ontimeapi.domain.team.model.UpdateTeamLeaderRequest;

@Builder
public record UpdateTeamLeaderV1Request(String userUniqueId)
    implements UpdateTeamLeaderRequest {
}
