package com.malgn.ontimeapi.domain.user.repository.query.impl;

import static com.malgn.ontimeapi.domain.position.entity.QPosition.*;
import static com.malgn.ontimeapi.domain.user.entity.QUserPosition.*;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.ontimeapi.domain.position.entity.QPosition;
import com.malgn.ontimeapi.domain.user.entity.UserPosition;
import com.malgn.ontimeapi.domain.user.repository.query.UserPositionQueryRepository;

@RequiredArgsConstructor
public class UserPositionQueryRepositoryImpl implements UserPositionQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Optional<UserPosition> getUserPosition(String userId) {
        return Optional.ofNullable(
            query.selectFrom(userPosition)
                .join(userPosition.position, position).fetchJoin()
                .where(userPosition.userId.eq(userId))
                .fetchOne());
    }
}
