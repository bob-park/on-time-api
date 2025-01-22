package com.malgn.ontimeapi.domain.role.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.malgn.ontimeapi.domain.role.model.RoleResponse;

@FeignClient(name = "auth-user-api", contextId = "auth-user-api-roles")
public interface RoleFeignClient {

    @GetMapping(path = "api/v1/roles")
    List<RoleResponse> getAll();
}
