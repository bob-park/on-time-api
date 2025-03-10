package com.malgn.ontimeapi.domain.attendance.repository.query.impl;

import static com.malgn.ontimeapi.domain.attendance.entity.QAttendanceCheck.*;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceType;
import com.malgn.ontimeapi.domain.attendance.repository.query.AttendanceCheckQueryRepository;

@RequiredArgsConstructor
public class AttendanceCheckQueryRepositoryImpl implements AttendanceCheckQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Optional<AttendanceCheck> currentCheck(LocalDateTime current, AttendanceType attendanceType) {
        return Optional.ofNullable(
            query.selectFrom(attendanceCheck)
                .where(
                    attendanceCheck.attendanceType.eq(attendanceType),
                    attendanceCheck.createdDate.before(current),
                    attendanceCheck.expiredDate.after(current))
                .fetchOne());
    }
}
