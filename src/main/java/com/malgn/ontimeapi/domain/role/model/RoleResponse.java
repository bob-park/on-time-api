package com.malgn.ontimeapi.domain.role.model;

import java.util.List;

import lombok.Builder;

import com.malgn.ontimeapi.domain.role.entity.type.RoleType;

@Builder
public record RoleResponse(Long id,
                           RoleType type,
                           String description,
                           List<RoleResponse> children) {

}
