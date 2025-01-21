package com.malgn.ontimeapi.domain.position.controller.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontimeapi.domain.position.model.PositionResponse;
import com.malgn.ontimeapi.domain.position.model.v1.CreatePositionV1Request;
import com.malgn.ontimeapi.domain.position.service.v1.PositionV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/position")
@PreAuthorize("hasRole('MANAGER')")
public class PositionV1Controller {

    private final PositionV1Service positionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public PositionResponse createPosition(@RequestBody CreatePositionV1Request createRequest) {
        return positionService.createPosition(createRequest);
    }

}
