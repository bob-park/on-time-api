package com.malgn.ontimeapi.domain.attendance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceStatus;
import com.malgn.ontimeapi.domain.attendance.entity.DayOffType;

public interface AttendanceRecordResponse {

    Long id();

    String userUniqueId();

    AttendanceStatus status();

    DayOffType dayOffType();

    LocalDate workingDate();

    LocalDateTime clockInTime();

    LocalDateTime leaveWorkAt();

    LocalDateTime clockOutTime();

    String message();

}
