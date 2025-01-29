package com.malgn.ontimeapi.domain.team.controller.v1;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;
import com.malgn.ontimeapi.domain.team.model.v1.CreateTeamV1Request;
import com.malgn.ontimeapi.domain.team.service.v1.TeamV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/teams")
public class TeamV1Controller {

    private final TeamV1Service teamService;

    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public TeamResponse createTeam(@RequestBody CreateTeamV1Request createRequest) {
        return teamService.createTeam(createRequest);
    }

    @GetMapping(path = "")
    public List<TeamResponse> getAll() {
        return teamService.getAll();
    }

    @GetMapping(path = "{teamId:\\d+}")
    public TeamResponse getTeam(@PathVariable Long teamId) {
        return teamService.getById(Id.of(Team.class, teamId));
    }
}
