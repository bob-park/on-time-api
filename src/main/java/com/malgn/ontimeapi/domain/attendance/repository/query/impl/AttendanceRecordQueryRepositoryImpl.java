package com.malgn.ontimeapi.domain.attendance.repository.query.impl;

import static com.malgn.ontimeapi.domain.attendance.entity.QAttendanceRecord.*;

import java.time.LocalDate;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceStatus;
import com.malgn.ontimeapi.domain.attendance.entity.QAttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.repository.query.AttendanceRecordQueryRepository;

@RequiredArgsConstructor
public class AttendanceRecordQueryRepositoryImpl implements AttendanceRecordQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Optional<AttendanceRecord> getWaitingByWorkingDate(String userUniqueId, LocalDate workingDate) {
        return Optional.ofNullable(
            query.selectFrom(attendanceRecord)
                .where(
                    attendanceRecord.userUniqueId.eq(userUniqueId),
                    attendanceRecord.workingDate.eq(workingDate),
                    attendanceRecord.clockInTime.isNull())
                .fetchOne());
    }

    @Override
    public Optional<AttendanceRecord> getClockInByWorkingDate(String userUniqueId, LocalDate workingDate) {
        return Optional.ofNullable(
            query.selectFrom(attendanceRecord)
                .where(
                    attendanceRecord.userUniqueId.eq(userUniqueId),
                    attendanceRecord.workingDate.eq(workingDate),
                    attendanceRecord.clockOutTime.isNull())
                .fetchOne());
    }
}
