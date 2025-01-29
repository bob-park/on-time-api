package com.malgn.ontimeapi.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.team.entity.TeamUser;
import com.malgn.ontimeapi.domain.team.repository.query.TeamUserQueryRepository;

public interface TeamUserRepository extends JpaRepository<TeamUser, Long>, TeamUserQueryRepository {
}
