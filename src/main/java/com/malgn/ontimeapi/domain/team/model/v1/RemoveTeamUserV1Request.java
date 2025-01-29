package com.malgn.ontimeapi.domain.team.model.v1;

import static org.apache.commons.lang3.ObjectUtils.*;

import java.util.List;

import com.malgn.ontimeapi.domain.team.model.RemoveTeamUserRequest;

public record RemoveTeamUserV1Request(List<String> userUniqueIds)
    implements RemoveTeamUserRequest {

    public RemoveTeamUserV1Request {
        userUniqueIds = defaultIfNull(userUniqueIds, List.of());
    }
}
