package com.malgn.ontimeapi.domain.role.model.v1;

import java.util.List;

import lombok.Builder;

import com.malgn.ontimeapi.domain.role.entity.Role;
import com.malgn.ontimeapi.domain.role.entity.type.RoleType;
import com.malgn.ontimeapi.domain.role.model.RoleResponse;

@Builder
public record RoleV1Response(Long id,
                             RoleType type,
                             String description,
                             RoleResponse parent,
                             List<? extends RoleResponse> children)
    implements RoleResponse {

    public static RoleResponse from(Role role) {
        return from(role, false);
    }

    public static RoleResponse from(Role role, boolean includeChildren) {
        return RoleV1Response.builder()
            .id(role.getId())
            .type(role.getType())
            .description(role.getDescription())
            .children(includeChildren ? role.getChildren().stream().map(RoleV1Response::from).toList() : List.of())
            .build();
    }


}
