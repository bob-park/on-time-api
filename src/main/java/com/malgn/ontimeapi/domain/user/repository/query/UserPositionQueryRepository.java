package com.malgn.ontimeapi.domain.user.repository.query;

import java.util.Optional;

import com.malgn.ontimeapi.domain.user.entity.UserPosition;

public interface UserPositionQueryRepository {

    Optional<UserPosition> getUserPosition(String userId);

}
