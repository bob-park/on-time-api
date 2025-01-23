package com.malgn.ontimeapi.domain.team.repository.query.impl;

import static com.malgn.ontimeapi.domain.team.entity.QTeam.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.ontimeapi.domain.team.entity.QTeam;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.repository.query.TeamQueryRepository;

@RequiredArgsConstructor
public class TeamQueryRepositoryImpl implements TeamQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<Team> getAll() {
        return query.selectFrom(team)
            .where(team.isDeleted.eq(false))
            .orderBy(team.createdDate.desc())
            .fetch();
    }
}
