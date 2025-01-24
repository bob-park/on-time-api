package com.malgn.ontimeapi.domain.attendance.model.v1;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheckType;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceCheckResponse;

@Builder
public record AttendanceCheckV1Response(String id,
                                        AttendanceCheckType type,
                                        LocalDate workingDate,
                                        LocalDateTime expiredDate,
                                        LocalDateTime createdDate,
                                        String createdBy,
                                        LocalDateTime lastModifiedDate,
                                        String lastModifiedBy)
    implements AttendanceCheckResponse {

    public static AttendanceCheckResponse from(AttendanceCheck attendanceCheck) {
        return AttendanceCheckV1Response.builder()
            .id(attendanceCheck.getId())
            .type(attendanceCheck.getType())
            .workingDate(attendanceCheck.getWorkingDate())
            .expiredDate(attendanceCheck.getExpiredDate())
            .createdDate(attendanceCheck.getCreatedDate())
            .createdBy(attendanceCheck.getCreatedBy())
            .lastModifiedDate(attendanceCheck.getLastModifiedDate())
            .lastModifiedBy(attendanceCheck.getLastModifiedBy())
            .build();
    }
}
