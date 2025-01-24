package com.malgn.ontimeapi.domain.attendance.repository.query;

import java.time.LocalDateTime;
import java.util.Optional;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceType;

public interface AttendanceCheckQueryRepository {

    Optional<AttendanceCheck> currentCheck(LocalDateTime current, AttendanceType attendanceType);

}
