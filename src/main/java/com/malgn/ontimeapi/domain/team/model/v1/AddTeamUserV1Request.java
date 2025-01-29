package com.malgn.ontimeapi.domain.team.model.v1;

import static org.apache.commons.lang3.ObjectUtils.*;

import java.util.List;

import com.malgn.ontimeapi.domain.team.model.AddTeamUserRequest;

public record AddTeamUserV1Request(List<String> userUniqueIds)
    implements AddTeamUserRequest {

    public AddTeamUserV1Request {
        userUniqueIds = defaultIfNull(userUniqueIds, List.of());
    }
}
