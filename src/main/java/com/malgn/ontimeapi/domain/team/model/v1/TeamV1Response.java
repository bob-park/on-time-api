package com.malgn.ontimeapi.domain.team.model.v1;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.entity.TeamUser;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;
import com.malgn.ontimeapi.domain.team.model.TeamUserResponse;

@Builder
public record TeamV1Response(Long id,
                             String name,
                             String description,
                             List<TeamUserResponse> teamUsers,
                             LocalDateTime createdDate,
                             String createdBy,
                             LocalDateTime lastModifiedDate,
                             String lastModifiedBy)
    implements TeamResponse {

    public static TeamResponse from(Team team) {
        return from(team, false);
    }

    public static TeamResponse from(Team team, boolean includeUsers) {
        return TeamV1Response.builder()
            .id(team.getId())
            .name(team.getName())
            .description(team.getDescription())
            .teamUsers(includeUsers ? team.getTeamUsers().stream().map(TeamUserV1Response::from).toList() : null)
            .createdDate(team.getCreatedDate())
            .createdBy(team.getCreatedBy())
            .lastModifiedDate(team.getLastModifiedDate())
            .lastModifiedBy(team.getLastModifiedBy())
            .build();
    }

}
