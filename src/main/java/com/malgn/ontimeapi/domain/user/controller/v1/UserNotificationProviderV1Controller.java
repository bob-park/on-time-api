package com.malgn.ontimeapi.domain.user.controller.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.user.model.UserNotificationProviderResponse;
import com.malgn.ontimeapi.domain.user.model.UserResponse;
import com.malgn.ontimeapi.domain.user.model.v1.AddNotificationProviderV1Request;
import com.malgn.ontimeapi.domain.user.service.v1.UserNotificationProviderV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users/{userUniqueId}/notification/provider")
@PreAuthorize("hasRole('MANAGER')")
public class UserNotificationProviderV1Controller {

    private final UserNotificationProviderV1Service userNotificationProviderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public UserNotificationProviderResponse addProvider(@PathVariable String userUniqueId,
        @RequestBody AddNotificationProviderV1Request addRequest) {
        return userNotificationProviderService.addProvider(Id.of(UserResponse.class, userUniqueId), addRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "")
    public UserNotificationProviderResponse removeProvider(@PathVariable String userUniqueId) {
        return userNotificationProviderService.removeProvider(Id.of(UserResponse.class, userUniqueId));
    }

}
