package com.malgn.ontimeapi.domain.attendance.model;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheckType;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceType;

public interface CurrentRequest {

    AttendanceCheckType type();

    AttendanceType attendanceType();
}
