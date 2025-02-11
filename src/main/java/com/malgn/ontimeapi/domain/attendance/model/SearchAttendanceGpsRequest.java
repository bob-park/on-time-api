package com.malgn.ontimeapi.domain.attendance.model;

import java.math.BigDecimal;

public interface SearchAttendanceGpsRequest {

    BigDecimal latitude();

    BigDecimal longitude();

}
