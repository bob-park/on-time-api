package com.malgn.ontimeapi.domain.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.role.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
