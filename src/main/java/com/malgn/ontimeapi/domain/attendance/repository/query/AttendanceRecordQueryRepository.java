package com.malgn.ontimeapi.domain.attendance.repository.query;

import java.time.LocalDate;
import java.util.Optional;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;

public interface AttendanceRecordQueryRepository {

    Optional<AttendanceRecord> getWaitingByWorkingDate(String userUniqueId, LocalDate workingDate);

    Optional<AttendanceRecord> getClockInByWorkingDate(String userUniqueId, LocalDate workingDate);

}
