package com.malgn.ontimeapi.domain.user.controller.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.user.entity.UserPosition;
import com.malgn.ontimeapi.domain.user.model.UserPositionResponse;
import com.malgn.ontimeapi.domain.user.service.v1.UserPositionV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/user/{userUniqueId}/position")
public class UserPositionV1Controller {

    private final UserPositionV1Service positionService;

    @GetMapping(path = "")
    public UserPositionResponse getUserPosition(@PathVariable("userUniqueId") String userUniqueId) {
        return positionService.getUserPosition(Id.of(UserPosition.class, userUniqueId));
    }

}
