package com.malgn.ontimeapi.domain.team.model.v1;

import com.malgn.ontimeapi.domain.team.model.CreateTeamRequest;

public record CreateTeamV1Request(String name,
                                  String description)
    implements CreateTeamRequest {
}
