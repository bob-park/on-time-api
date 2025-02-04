package com.malgn.ontimeapi.domain.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceGps;
import com.malgn.ontimeapi.domain.attendance.repository.query.AttendanceGpsQueryRepository;

public interface AttendanceGpsRepository extends JpaRepository<AttendanceGps, Long>,
    AttendanceGpsQueryRepository {
}
