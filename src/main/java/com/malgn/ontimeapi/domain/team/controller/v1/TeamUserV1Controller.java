package com.malgn.ontimeapi.domain.team.controller.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;
import com.malgn.ontimeapi.domain.team.model.v1.AddTeamUserV1Request;
import com.malgn.ontimeapi.domain.team.model.v1.RemoveTeamUserV1Request;
import com.malgn.ontimeapi.domain.team.service.v1.TeamUserV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/teams/{teamId:\\d+}/users")
public class TeamUserV1Controller {

    private final TeamUserV1Service teamUserService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public TeamResponse addTeamUser(@PathVariable long teamId, @RequestBody AddTeamUserV1Request addRequest) {
        return teamUserService.addTeamUsers(Id.of(Team.class, teamId), addRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "")
    public TeamResponse removeTeamUser(@PathVariable long teamId, @RequestBody RemoveTeamUserV1Request removeRequest) {
        return teamUserService.removeTeamUsers(Id.of(Team.class, teamId), removeRequest);
    }

}
