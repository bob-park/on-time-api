package com.malgn.ontimeapi.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
