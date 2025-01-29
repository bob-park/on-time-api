package com.malgn.ontimeapi.domain.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.repository.query.AttendanceRecordQueryRepository;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long>,
    AttendanceRecordQueryRepository {
}
