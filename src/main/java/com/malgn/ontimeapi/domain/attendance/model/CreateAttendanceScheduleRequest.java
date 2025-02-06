package com.malgn.ontimeapi.domain.attendance.model;

import java.time.LocalDate;

import com.malgn.ontimeapi.domain.attendance.entity.DayOffType;

public interface CreateAttendanceScheduleRequest {

    String userUniqueId();

    DayOffType dayOffType();

    LocalDate workingDate();

}
