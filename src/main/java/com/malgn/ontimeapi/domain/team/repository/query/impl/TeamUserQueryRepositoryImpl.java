package com.malgn.ontimeapi.domain.team.repository.query.impl;

import static com.malgn.ontimeapi.domain.team.entity.QTeam.*;
import static com.malgn.ontimeapi.domain.team.entity.QTeamUser.*;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.entity.TeamUser;
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

    @Override
    public Optional<TeamUser> getTeamUser(Id<Team, Long> teamId, Id<TeamUser, String> userUniqueId) {
        return Optional.ofNullable(
            query.selectFrom(teamUser)
                .join(teamUser.team, team).fetchJoin()
                .where(
                    team.id.eq(teamId.getValue()),
                    team.isDeleted.eq(false),
                    teamUser.userUniqueId.eq(userUniqueId.getValue()))
                .fetchOne());
    }

    @Override
    public long updateAllNonLeaders(Id<Team, Long> teamId) {
        return query.update(teamUser)
            .set(teamUser.isLeader, false)
            .where(
                teamUser.team.id.eq(teamId.getValue()),
                teamUser.isLeader.eq(true))
            .execute();
    }
}
