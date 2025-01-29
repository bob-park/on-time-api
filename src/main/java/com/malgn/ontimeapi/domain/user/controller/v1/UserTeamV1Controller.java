package com.malgn.ontimeapi.domain.user.controller.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;
import com.malgn.ontimeapi.domain.user.model.UserResponse;
import com.malgn.ontimeapi.domain.user.service.v1.UserTeamV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users/{uniqueId}/team")
public class UserTeamV1Controller {
    private final UserTeamV1Service userTeamService;

    @GetMapping(path = "")
    public TeamResponse getTeam(@PathVariable("uniqueId") String uniqueId) {
        return userTeamService.getTeam(Id.of(UserResponse.class, uniqueId));
    }
}
