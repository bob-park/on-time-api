package com.malgn.ontimeapi.domain.team.repository.query;

import java.util.List;
import java.util.Optional;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.entity.TeamUser;

public interface TeamUserQueryRepository {

    long deleteTeamUser(Id<Team, Long> teamId, String userUniqueId);

    Optional<TeamUser> getTeamUser(Id<Team, Long> teamId, Id<TeamUser, String> userUniqueId);

    long updateAllNonLeaders(Id<Team, Long> teamId);

    Optional<TeamUser> getTeamByUser(String userUniqueId);

    List<TeamUser> getLeaders(Id<Team, Long> teamId);

}
