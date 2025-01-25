package com.malgn.ontimeapi.domain.user.controller.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontimeapi.domain.user.model.UserPositionResponse;
import com.malgn.ontimeapi.domain.user.model.v1.UpdateUserPositionV1Request;
import com.malgn.ontimeapi.domain.user.service.v1.UserPositionV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users/{uniqueId}/position")
public class UserPositionV1Controller {

    private final UserPositionV1Service positionService;

    @PostMapping(path = "")
    public UserPositionResponse updatePosition(@PathVariable String uniqueId,
        @RequestBody UpdateUserPositionV1Request updateRequest) {
        return positionService.updatePosition(uniqueId, updateRequest);
    }

    @GetMapping(path = "")
    public UserPositionResponse getUserPosition(@PathVariable String uniqueId) {
        return positionService.getUserPosition(uniqueId);
    }

}
