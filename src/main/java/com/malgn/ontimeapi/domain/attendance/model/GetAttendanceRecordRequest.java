package com.malgn.ontimeapi.domain.attendance.model;

import java.time.LocalDate;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceStatus;
import com.malgn.ontimeapi.domain.attendance.entity.DayOffType;


public interface GetAttendanceRecordRequest {

    LocalDate startDate();

    LocalDate endDate();

    String userUniqueId();

    AttendanceStatus status();

    DayOffType dayOffType();

}
