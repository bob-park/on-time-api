package com.malgn.ontimeapi.domain.team.service;

import java.util.List;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.model.CreateTeamRequest;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;

public interface TeamService {

    TeamResponse createTeam(CreateTeamRequest createRequest);

    List<TeamResponse> getAll();

    TeamResponse getById(Id<Team, Long> teamId);

}
