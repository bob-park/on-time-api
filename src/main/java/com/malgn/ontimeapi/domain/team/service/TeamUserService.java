package com.malgn.ontimeapi.domain.team.service;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.model.AddTeamUserRequest;
import com.malgn.ontimeapi.domain.team.model.RemoveTeamUserRequest;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;

public interface TeamUserService {

    TeamResponse addTeamUsers(Id<Team, Long> teamId, AddTeamUserRequest addRequest);

    TeamResponse removeTeamUsers(Id<Team, Long> teamId, RemoveTeamUserRequest removeRequest);

}
