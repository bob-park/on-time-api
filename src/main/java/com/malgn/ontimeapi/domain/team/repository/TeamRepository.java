package com.malgn.ontimeapi.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.repository.query.TeamQueryRepository;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamQueryRepository {
}
