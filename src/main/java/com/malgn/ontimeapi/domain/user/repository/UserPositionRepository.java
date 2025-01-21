package com.malgn.ontimeapi.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.user.entity.UserPosition;
import com.malgn.ontimeapi.domain.user.repository.query.UserPositionQueryRepository;

public interface UserPositionRepository extends JpaRepository<UserPosition, Long>, UserPositionQueryRepository {
}
