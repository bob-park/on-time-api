package com.malgn.ontimeapi.domain.attendance.model.v2;

import java.math.BigDecimal;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecordWorkType;
import com.malgn.ontimeapi.domain.attendance.model.ClockInRequest;

public record ClockInRequestV2(String userUniqueId,
                               AttendanceRecordWorkType workType,
                               BigDecimal latitude,
                               BigDecimal longitude)
    implements ClockInRequest {
}
