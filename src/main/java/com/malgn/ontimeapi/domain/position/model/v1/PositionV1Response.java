package com.malgn.ontimeapi.domain.position.model.v1;

import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.ontimeapi.domain.position.entity.Position;
import com.malgn.ontimeapi.domain.position.model.PositionResponse;

@Builder
public record PositionV1Response(Long id,
                                 String name,
                                 String description,
                                 LocalDateTime createdDate,
                                 String createdBy,
                                 LocalDateTime lastModifiedDate,
                                 String lastModifiedBy)
    implements PositionResponse {

    public static PositionResponse from(Position position) {
        return PositionV1Response.builder()
            .id(position.getId())
            .name(position.getName())
            .description(position.getDescription())
            .createdDate(position.getCreatedDate())
            .createdBy(position.getCreatedBy())
            .lastModifiedDate(position.getLastModifiedDate())
            .lastModifiedBy(position.getLastModifiedBy())
            .build();
    }

}
