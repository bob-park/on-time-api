package com.malgn.ontimeapi.domain.attendance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheckSubType;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheckType;

public interface AttendanceCheckResponse {

    String id();

    AttendanceCheckType type();

    AttendanceCheckSubType subType();

    LocalDate workingDate();

    LocalDateTime expiredDate();

}
