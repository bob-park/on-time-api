package com.malgn.ontimeapi.domain.attendance.model.v2;

import java.time.LocalDate;

import lombok.Builder;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceStatus;
import com.malgn.ontimeapi.domain.attendance.entity.DayOffType;
import com.malgn.ontimeapi.domain.attendance.model.GetAttendanceRecordRequest;

@Builder(toBuilder = true)
public record GetAttendanceRecordRequestV2(LocalDate startDate,
                                           LocalDate endDate,
                                           String userUniqueId,
                                           AttendanceStatus status,
                                           DayOffType dayOffType)
    implements GetAttendanceRecordRequest {
}
