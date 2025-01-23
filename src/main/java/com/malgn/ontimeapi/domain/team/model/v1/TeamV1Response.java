package com.malgn.ontimeapi.domain.team.model.v1;

import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;

@Builder
public record TeamV1Response(Long id,
                             String name,
                             String description,
                             LocalDateTime createdDate,
                             String createdBy,
                             LocalDateTime lastModifiedDate,
                             String lastModifiedBy)
    implements TeamResponse {

    public static TeamResponse from(Team team) {
        return TeamV1Response.builder()
            .id(team.getId())
            .name(team.getName())
            .description(team.getDescription())
            .createdDate(team.getCreatedDate())
            .createdBy(team.getCreatedBy())
            .lastModifiedDate(team.getLastModifiedDate())
            .lastModifiedBy(team.getLastModifiedBy())
            .build();
    }

}
