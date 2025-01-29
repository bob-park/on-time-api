package com.malgn.ontimeapi.domain.position.repository.query.impl;

import static com.malgn.ontimeapi.domain.position.entity.QPosition.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.ontimeapi.domain.position.entity.Position;
import com.malgn.ontimeapi.domain.position.entity.QPosition;
import com.malgn.ontimeapi.domain.position.repository.query.PositionQueryRepository;

@RequiredArgsConstructor
public class PositionQueryRepositoryImpl implements PositionQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<Position> getAll() {
        return query.selectFrom(position)
            .orderBy(position.createdDate.desc())
            .fetch();
    }
}
