package com.malgn.ontimeapi.domain.attendance.repository.query.impl;

import static com.malgn.ontimeapi.domain.attendance.entity.QAttendanceGps.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceGps;
import com.malgn.ontimeapi.domain.attendance.entity.QAttendanceGps;
import com.malgn.ontimeapi.domain.attendance.repository.query.AttendanceGpsQueryRepository;

@RequiredArgsConstructor
public class AttendanceGpsQueryRepositoryImpl implements AttendanceGpsQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<AttendanceGps> getAllGps() {
        return query.selectFrom(attendanceGps)
            .orderBy(attendanceGps.createdDate.asc())
            .fetch();
    }

    @Override
    public Optional<AttendanceGps> getGps(BigDecimal latitude, BigDecimal longitude) {
        return Optional.ofNullable(
            query.selectFrom(attendanceGps)
                .where(
                    attendanceGps.latitude.eq(latitude),
                    attendanceGps.longitude.eq(longitude))
                .fetchOne());
    }
}
