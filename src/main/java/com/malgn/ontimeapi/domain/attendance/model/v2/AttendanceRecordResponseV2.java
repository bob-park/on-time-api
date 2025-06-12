package com.malgn.ontimeapi.domain.attendance.model.v2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecordWorkType;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceStatus;
import com.malgn.ontimeapi.domain.attendance.entity.DayOffType;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;

@Builder
public record AttendanceRecordResponseV2(Long id,
                                         String userUniqueId,
                                         AttendanceStatus status,
                                         DayOffType dayOffType,
                                         AttendanceRecordWorkType workType,
                                         LocalDate workingDate,
                                         LocalDateTime clockInTime,
                                         BigDecimal clockInLatitude,
                                         BigDecimal clockInLongitude,
                                         LocalDateTime leaveWorkAt,
                                         LocalDateTime clockOutTime,
                                         BigDecimal clockOutLatitude,
                                         BigDecimal clockOutLongitude,
                                         String message,
                                         LocalDateTime createdDate,
                                         String createdBy,
                                         LocalDateTime lastModifiedDate,
                                         String lastModifiedBy)
    implements AttendanceRecordResponse {

    public static AttendanceRecordResponse from(AttendanceRecord attendanceRecord) {
        return AttendanceRecordResponseV2.builder()
            .id(attendanceRecord.getId())
            .userUniqueId(attendanceRecord.getUserUniqueId())
            .status(attendanceRecord.getStatus())
            .dayOffType(attendanceRecord.getDayOffType())
            .workType(attendanceRecord.getWorkType())
            .workingDate(attendanceRecord.getWorkingDate())
            .clockInTime(attendanceRecord.getClockInTime())
            .clockInLatitude(attendanceRecord.getClockInLatitude())
            .clockInLongitude(attendanceRecord.getClockInLongitude())
            .leaveWorkAt(attendanceRecord.getLeaveWorkAt())
            .clockOutTime(attendanceRecord.getClockOutTime())
            .clockOutLatitude(attendanceRecord.getClockOutLatitude())
            .clockOutLongitude(attendanceRecord.getClockOutLongitude())
            .message(attendanceRecord.getMessage())
            .createdDate(attendanceRecord.getCreatedDate())
            .createdBy(attendanceRecord.getCreatedBy())
            .lastModifiedDate(attendanceRecord.getLastModifiedDate())
            .lastModifiedBy(attendanceRecord.getLastModifiedBy())
            .build();
    }
}
