package com.malgn.ontimeapi.domain.team.model.v1;

import static org.apache.commons.lang3.ObjectUtils.*;

import lombok.Builder;

import com.malgn.ontimeapi.domain.team.model.UpdateTeamUserRequest;

@Builder
public record UpdateTeamUserV1Request(String userUniqueId,
                                      Boolean isLeader)
    implements UpdateTeamUserRequest {

    public UpdateTeamUserV1Request {
        isLeader = defaultIfNull(isLeader, false);
    }
}
