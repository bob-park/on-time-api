package com.malgn.ontimeapi.domain.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.repository.query.AttendanceCheckQueryRepository;

public interface AttendanceCheckRepository extends JpaRepository<AttendanceCheck, String>,
    AttendanceCheckQueryRepository {
}
