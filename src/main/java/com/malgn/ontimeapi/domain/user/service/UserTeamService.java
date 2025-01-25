package com.malgn.ontimeapi.domain.user.service;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;
import com.malgn.ontimeapi.domain.user.model.UserResponse;

public interface UserTeamService {

    TeamResponse getTeam(Id<UserResponse, String> unqiueId);

}
