package com.malgn.ontimeapi.domain.attendance.model.v1;

import lombok.Builder;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordRequest;

@Builder
public record AttendanceRecordV1Request(String checkId,
                                        String userUniqueId)
    implements AttendanceRecordRequest {
}
