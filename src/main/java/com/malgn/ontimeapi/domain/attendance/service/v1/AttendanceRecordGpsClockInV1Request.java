package com.malgn.ontimeapi.domain.attendance.service.v1;

import java.math.BigDecimal;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordGpsClockInRequest;

public record AttendanceRecordGpsClockInV1Request(Long gpsId,
                                                  String userUniqueId,
                                                  BigDecimal latitude,
                                                  BigDecimal longitude)
    implements AttendanceRecordGpsClockInRequest {
}
