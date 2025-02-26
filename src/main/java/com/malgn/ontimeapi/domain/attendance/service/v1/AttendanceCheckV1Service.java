package com.malgn.ontimeapi.domain.attendance.service.v1;

import static com.google.common.base.Preconditions.*;
import static com.malgn.ontimeapi.domain.attendance.model.v1.AttendanceCheckV1Response.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.common.model.Id;
import com.malgn.lock.annotation.DistributedLock;
import com.malgn.ontimeapi.configure.ontime.properties.OnTimeProperties;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceCheckResponse;
import com.malgn.ontimeapi.domain.attendance.model.CurrentRequest;
import com.malgn.ontimeapi.domain.attendance.model.v1.CurrentV1Request;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceCheckRepository;
import com.malgn.ontimeapi.domain.attendance.service.AttendanceCheckService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AttendanceCheckV1Service implements AttendanceCheckService {

    private static final String LOCK_KEY = "current_attendance_check";

    private final OnTimeProperties properties;

    private final AttendanceCheckRepository attendanceCheckRepository;

    @DistributedLock(key = LOCK_KEY)
    @Transactional
    @Override
    public AttendanceCheckResponse currentCheck(CurrentRequest currentRequest) {

        CurrentV1Request currentV1Request = (CurrentV1Request)currentRequest;

        checkArgument(isNotEmpty(currentV1Request.type()), "type must be provided.");
        checkArgument(isNotEmpty(currentV1Request.attendanceType()), "attendanceType must be provided.");

        LocalDateTime now = LocalDateTime.now();

        AttendanceCheck attendanceCheck =
            attendanceCheckRepository.currentCheck(now, currentV1Request.attendanceType())
                .orElseGet(() -> {
                    AttendanceCheck createdCheck =
                        AttendanceCheck.builder()
                            .type(currentV1Request.type())
                            .attendanceType(currentV1Request.attendanceType())
                            .workingDate(now.toLocalDate())
                            .expiredDate(now.plus(properties.attendance().expiredCheck()))
                            .build();

                    createdCheck = attendanceCheckRepository.save(createdCheck);

                    log.debug("created attendance check: {}", createdCheck);

                    return createdCheck;
                });

        return from(attendanceCheck);
    }

    @Override
    public AttendanceCheckResponse getCheck(Id<AttendanceCheck, String> attendanceCheckId) {
        return null;
    }
}
