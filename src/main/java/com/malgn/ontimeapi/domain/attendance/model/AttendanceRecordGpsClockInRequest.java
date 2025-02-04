package com.malgn.ontimeapi.domain.attendance.model;

import java.math.BigDecimal;

public interface AttendanceRecordGpsClockInRequest {

    Long gpsId();

    String userUniqueId();

    BigDecimal latitude();

    BigDecimal longitude();

}
