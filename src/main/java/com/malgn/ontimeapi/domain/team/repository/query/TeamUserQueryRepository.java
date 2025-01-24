package com.malgn.ontimeapi.domain.team.repository.query;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;

public interface TeamUserQueryRepository {

    long deleteTeamUser(Id<Team, Long> teamId, String userUniqueId);

}
