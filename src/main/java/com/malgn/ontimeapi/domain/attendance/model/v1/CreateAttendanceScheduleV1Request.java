package com.malgn.ontimeapi.domain.attendance.model.v1;

import java.time.LocalDate;

import lombok.Builder;

import com.malgn.ontimeapi.domain.attendance.entity.DayOffType;
import com.malgn.ontimeapi.domain.attendance.model.CreateAttendanceScheduleRequest;

@Builder
public record CreateAttendanceScheduleV1Request(String userUniqueId,
                                                DayOffType dayOffType,
                                                LocalDate workingDate)
    implements CreateAttendanceScheduleRequest {
}
