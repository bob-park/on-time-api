package com.malgn.ontimeapi.domain.attendance.repository.query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.model.GetAttendanceRecordRequest;

public interface AttendanceRecordQueryRepository {

    boolean existRecord(String userUniqueId, LocalDate workingDate);

    Optional<AttendanceRecord> getWaitingByWorkingDate(String userUniqueId, LocalDate workingDate);

    Optional<AttendanceRecord> getClockInByWorkingDate(String userUniqueId, LocalDate workingDate);

    List<AttendanceRecord> getAllRecords(GetAttendanceRecordRequest getRequest);

}
