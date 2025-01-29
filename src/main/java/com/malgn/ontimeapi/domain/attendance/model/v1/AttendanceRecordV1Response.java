package com.malgn.ontimeapi.domain.attendance.model.v1;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceStatus;
import com.malgn.ontimeapi.domain.attendance.entity.DayOffType;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;

@Builder
public record AttendanceRecordV1Response(Long id,
                                         String userUniqueId,
                                         AttendanceStatus status,
                                         DayOffType dayOffType,
                                         LocalDate workingDate,
                                         LocalDateTime clockInTime,
                                         LocalDateTime leaveWorkAt,
                                         LocalDateTime clockOutTime,
                                         String message,
                                         LocalDateTime createdDate,
                                         String createdBy,
                                         LocalDateTime lastModifiedDate,
                                         String lastModifiedBy)
    implements AttendanceRecordResponse {

    public static AttendanceRecordResponse from(AttendanceRecord attendanceRecord) {
        return AttendanceRecordV1Response.builder()
            .id(attendanceRecord.getId())
            .userUniqueId(attendanceRecord.getUserUniqueId())
            .status(attendanceRecord.getStatus())
            .dayOffType(attendanceRecord.getDayOffType())
            .workingDate(attendanceRecord.getWorkingDate())
            .clockInTime(attendanceRecord.getClockInTime())
            .leaveWorkAt(attendanceRecord.getLeaveWorkAt())
            .clockOutTime(attendanceRecord.getClockOutTime())
            .message(attendanceRecord.getMessage())
            .createdDate(attendanceRecord.getCreatedDate())
            .createdBy(attendanceRecord.getCreatedBy())
            .lastModifiedDate(attendanceRecord.getLastModifiedDate())
            .lastModifiedBy(attendanceRecord.getLastModifiedBy())
            .build();
    }
}
