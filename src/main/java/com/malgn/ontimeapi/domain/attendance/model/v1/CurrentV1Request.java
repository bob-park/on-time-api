package com.malgn.ontimeapi.domain.attendance.model.v1;

import lombok.Builder;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceType;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheckType;
import com.malgn.ontimeapi.domain.attendance.model.CurrentRequest;

@Builder
public record CurrentV1Request(AttendanceCheckType type,
                               AttendanceType attendanceType)
    implements CurrentRequest {

}
