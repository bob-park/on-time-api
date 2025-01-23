package com.malgn.ontimeapi.domain.position.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.position.entity.Position;
import com.malgn.ontimeapi.domain.position.repository.query.PositionQueryRepository;

public interface PositionRepository extends JpaRepository<Position, Long>, PositionQueryRepository {
}
