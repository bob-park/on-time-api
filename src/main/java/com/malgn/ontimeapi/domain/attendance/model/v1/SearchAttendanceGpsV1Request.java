package com.malgn.ontimeapi.domain.attendance.model.v1;

import java.math.BigDecimal;

import com.malgn.ontimeapi.domain.attendance.model.SearchAttendanceGpsRequest;

public record SearchAttendanceGpsV1Request(BigDecimal latitude,
                                           BigDecimal longitude)
    implements SearchAttendanceGpsRequest {
}
