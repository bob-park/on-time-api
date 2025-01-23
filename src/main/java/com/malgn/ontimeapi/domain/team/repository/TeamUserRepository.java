package com.malgn.ontimeapi.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.team.entity.TeamUser;

public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {
}
