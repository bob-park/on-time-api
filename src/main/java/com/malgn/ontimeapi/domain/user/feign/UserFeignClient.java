package com.malgn.ontimeapi.domain.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.malgn.ontimeapi.common.page.SimplePage;
import com.malgn.ontimeapi.domain.user.model.UserResponse;

@FeignClient(name = "auth-user-api", contextId = "auth-user-api")
public interface UserFeignClient {

    @GetMapping(path = "api/v1/users/{uniqueId}")
    UserResponse getById(@PathVariable String uniqueId);

    @GetMapping(path = "api/v1/users")
    SimplePage<UserResponse> getAll(Pageable pageable);
}
