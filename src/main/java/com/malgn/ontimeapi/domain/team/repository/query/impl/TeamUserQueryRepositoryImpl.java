package com.malgn.ontimeapi.domain.team.repository.query.impl;

import static com.malgn.ontimeapi.domain.team.entity.QTeamUser.*;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.repository.query.TeamUserQueryRepository;

@RequiredArgsConstructor
public class TeamUserQueryRepositoryImpl implements TeamUserQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public long deleteTeamUser(Id<Team, Long> teamId, String userUniqueId) {
        return query.delete(teamUser)
            .where(
                teamUser.team.id.eq(teamId.getValue()),
                teamUser.userUniqueId.eq(userUniqueId))
            .execute();
    }
}
