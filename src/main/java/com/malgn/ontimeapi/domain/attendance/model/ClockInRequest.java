package com.malgn.ontimeapi.domain.attendance.model;

import java.math.BigDecimal;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecordWorkType;

public interface ClockInRequest {

    String userUniqueId();

    AttendanceRecordWorkType workType();

    BigDecimal latitude();

    BigDecimal longitude();

}
