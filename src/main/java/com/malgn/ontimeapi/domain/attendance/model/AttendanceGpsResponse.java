package com.malgn.ontimeapi.domain.attendance.model;

import com.malgn.common.model.CommonResponse;

public interface AttendanceGpsResponse extends CommonResponse {
    Long id();

    String name();

    String description();

}
