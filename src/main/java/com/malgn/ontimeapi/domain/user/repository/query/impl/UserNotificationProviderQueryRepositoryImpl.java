package com.malgn.ontimeapi.domain.user.repository.query.impl;

import static com.malgn.ontimeapi.domain.user.entity.QUserNotificationProvider.*;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.ontimeapi.domain.user.entity.QUserNotificationProvider;
import com.malgn.ontimeapi.domain.user.entity.UserNotificationProvider;
import com.malgn.ontimeapi.domain.user.repository.query.UserNotificationProviderQueryRepository;

@RequiredArgsConstructor
public class UserNotificationProviderQueryRepositoryImpl implements UserNotificationProviderQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Optional<UserNotificationProvider> getProvider(String userUniqueId) {
        return Optional.ofNullable(
            query.selectFrom(userNotificationProvider)
                .where(userNotificationProvider.userUniqueId.eq(userUniqueId))
                .fetchOne());
    }

    @Override
    public void deleteProvider(String userUniqueId) {
        query.delete(userNotificationProvider)
            .where(userNotificationProvider.userUniqueId.eq(userUniqueId))
            .execute();
    }
}
