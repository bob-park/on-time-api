package com.malgn.ontimeapi.domain.attendance.service.v1;

import static com.google.common.base.Preconditions.*;
import static com.malgn.ontimeapi.domain.attendance.model.v1.AttendanceRecordV1Response.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

import com.malgn.common.exception.AlreadyExistException;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontimeapi.domain.attendance.model.CreateAttendanceScheduleRequest;
import com.malgn.ontimeapi.domain.attendance.model.v1.AttendanceRecordV1Response;
import com.malgn.ontimeapi.domain.attendance.model.v1.CreateAttendanceScheduleV1Request;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceRecordRepository;
import com.malgn.ontimeapi.domain.attendance.service.AttendanceScheduleService;
import com.malgn.ontimeapi.domain.user.feign.UserFeignClient;
import com.malgn.ontimeapi.domain.user.model.UserResponse;
import com.malgn.ontimeapi.utils.AuthUtils;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AttendanceScheduleV1Service implements AttendanceScheduleService {

    private final UserFeignClient userClient;

    private final AttendanceRecordRepository attendanceRecordRepository;

    @Transactional
    @Override
    public AttendanceRecordResponse createSchedule(CreateAttendanceScheduleRequest createRequest) {

        CreateAttendanceScheduleV1Request createV1Request = (CreateAttendanceScheduleV1Request)createRequest;

        checkArgument(StringUtils.isNotBlank(createV1Request.userUniqueId()), "userUniqueId must be provided.");
        checkArgument(isNotEmpty(createV1Request.workingDate()), "workingDate must be provided.");

        String currentUserId = AuthUtils.getCurrentUserId();
        UserResponse user = userClient.getById(createV1Request.userUniqueId());

        checkArgument(StringUtils.equals(currentUserId, user.userId()), "Not match login account and record account");

        boolean existRecord =
            attendanceRecordRepository.existRecord(createV1Request.userUniqueId(), createRequest.workingDate());

        if (existRecord) {
            throw new AlreadyExistException("Schedule already exists");
        }

        AttendanceRecord createdRecord =
            AttendanceRecord.builder()
                .userUniqueId(createV1Request.userUniqueId())
                .dayOffType(createV1Request.dayOffType())
                .workingDate(createV1Request.workingDate())
                .build();

        createdRecord = attendanceRecordRepository.save(createdRecord);

        log.debug("created attendance schedule: {}", createdRecord);

        return from(createdRecord);
    }
}
