package com.malgn.ontimeapi.domain.attendance.repository.query.impl;

import static com.malgn.ontimeapi.domain.attendance.entity.QAttendanceRecord.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceStatus;
import com.malgn.ontimeapi.domain.attendance.entity.DayOffType;
import com.malgn.ontimeapi.domain.attendance.entity.QAttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.model.GetAttendanceRecordRequest;
import com.malgn.ontimeapi.domain.attendance.model.v1.GetAttendanceRecordV1Request;
import com.malgn.ontimeapi.domain.attendance.repository.query.AttendanceRecordQueryRepository;

@RequiredArgsConstructor
public class AttendanceRecordQueryRepositoryImpl implements AttendanceRecordQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public boolean existRecord(String userUniqueId, LocalDate workingDate) {
        Long result =
            query.select(attendanceRecord.id.count()).from(attendanceRecord)
                .where(
                    attendanceRecord.userUniqueId.eq(userUniqueId),
                    attendanceRecord.workingDate.eq(workingDate))
                .fetchOne();

        return defaultIfNull(result, 0L) > 0;
    }

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
                    attendanceRecord.clockInTime.isNotNull())
                .fetchOne());
    }

    @Override
    public List<AttendanceRecord> getAllRecords(GetAttendanceRecordRequest getRequest) {
        return query.selectFrom(attendanceRecord)
            .where(mappingCondition(getRequest))
            .orderBy(attendanceRecord.workingDate.asc())
            .fetch();
    }

    /*
     * mapping condition
     */
    private Predicate mappingCondition(GetAttendanceRecordRequest getRequest) {
        BooleanBuilder builder = new BooleanBuilder();

        if (getRequest.getClass().isAssignableFrom(GetAttendanceRecordV1Request.class)) {
            // v1
            GetAttendanceRecordV1Request getV1Request = (GetAttendanceRecordV1Request)getRequest;

            builder.and(eqUserUniqueId(getV1Request.userUniqueId()))
                .and(goeWorkingDate(getV1Request.startDate()))
                .and(loeWorkingDate(getV1Request.endDate()))
                .and(eqStatus(getV1Request.status()))
                .and(eqDayOffType(getV1Request.dayOffType()));
        }

        return builder;
    }

    private BooleanExpression eqUserUniqueId(String userUniqueId) {
        return StringUtils.isNotBlank(userUniqueId) ? attendanceRecord.userUniqueId.eq(userUniqueId) : null;
    }

    private BooleanExpression goeWorkingDate(LocalDate startDate) {
        return startDate != null ? attendanceRecord.workingDate.goe(startDate) : null;
    }

    private BooleanExpression loeWorkingDate(LocalDate endDate) {
        return endDate != null ? attendanceRecord.workingDate.loe(endDate) : null;
    }

    private BooleanExpression eqStatus(AttendanceStatus status) {
        return status != null ? attendanceRecord.status.eq(status) : null;
    }

    private BooleanExpression eqDayOffType(DayOffType dayOffType) {
        return dayOffType != null ? attendanceRecord.dayOffType.eq(dayOffType) : null;
    }
}
