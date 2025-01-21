package com.malgn.ontimeapi.domain.position.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.position.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
