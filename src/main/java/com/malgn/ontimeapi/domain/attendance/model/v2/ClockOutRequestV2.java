package com.malgn.ontimeapi.domain.attendance.model.v2;

import java.math.BigDecimal;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecordWorkType;
import com.malgn.ontimeapi.domain.attendance.model.ClockInRequest;
import com.malgn.ontimeapi.domain.attendance.model.ClockOutRequest;

public record ClockOutRequestV2(String userUniqueId,
                                Long attendanceRecordId,
                                BigDecimal latitude,
                                BigDecimal longitude)
    implements ClockOutRequest {
}
