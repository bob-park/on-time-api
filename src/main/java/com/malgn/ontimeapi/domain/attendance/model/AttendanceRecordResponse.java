package com.malgn.ontimeapi.domain.attendance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AttendanceRecordResponse {

    Long id();

    String userId();

    LocalDate workingDate();

    LocalDateTime clockInTime();

    LocalDateTime leaveWorkAt();

    LocalDateTime clockOutTime();

    String message();

}
