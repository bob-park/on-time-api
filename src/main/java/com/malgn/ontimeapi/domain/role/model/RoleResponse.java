package com.malgn.ontimeapi.domain.role.model;

import java.util.List;

import com.malgn.ontimeapi.domain.role.entity.type.RoleType;

public interface RoleResponse {

    RoleType type();

    String description();

    RoleResponse parent();

    List<? extends RoleResponse> children();

}
