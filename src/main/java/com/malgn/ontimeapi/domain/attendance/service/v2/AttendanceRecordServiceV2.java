package com.malgn.ontimeapi.domain.attendance.service.v2;

import static com.google.common.base.Preconditions.*;
import static com.malgn.ontimeapi.domain.attendance.model.v2.AttendanceRecordResponseV2.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang3.StringUtils;

import com.malgn.common.exception.NotFoundException;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontimeapi.domain.attendance.model.ClockInRequest;
import com.malgn.ontimeapi.domain.attendance.model.ClockOutRequest;
import com.malgn.ontimeapi.domain.attendance.model.GetAttendanceRecordRequest;
import com.malgn.ontimeapi.domain.attendance.model.v2.AttendanceRecordResponseV2;
import com.malgn.ontimeapi.domain.attendance.model.v2.ClockInRequestV2;
import com.malgn.ontimeapi.domain.attendance.model.v2.ClockOutRequestV2;
import com.malgn.ontimeapi.domain.attendance.model.v2.GetAttendanceRecordRequestV2;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceRecordRepository;
import com.malgn.ontimeapi.domain.attendance.service.AttendanceRecordService;
import com.malgn.ontimeapi.domain.user.feign.UserFeignClient;
import com.malgn.ontimeapi.domain.user.model.UserResponse;
import com.malgn.ontimeapi.utils.AuthUtils;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AttendanceRecordServiceV2 implements AttendanceRecordService {

    private final UserFeignClient userClient;

    private final AttendanceRecordRepository attendanceRecordRepository;

    @Override
    public List<AttendanceRecordResponse> getRecords(GetAttendanceRecordRequest getRequest) {
        GetAttendanceRecordRequestV2 getV2Request = (GetAttendanceRecordRequestV2)getRequest;

        checkArgument(StringUtils.isNotBlank(getV2Request.userUniqueId()), "userUniqueId must be provided.");

        List<AttendanceRecord> result = attendanceRecordRepository.getAllRecords(getV2Request);

        return result.stream()
            .map(AttendanceRecordResponseV2::from)
            .toList();
    }

    @Transactional
    @Override
    public AttendanceRecordResponse clockIn(ClockInRequest clockInRequest) {

        ClockInRequestV2 requestV2 = (ClockInRequestV2)clockInRequest;

        checkArgument(StringUtils.isNotBlank(requestV2.userUniqueId()), "userUniqueId must be provided.");
        checkArgument(isNotEmpty(requestV2.workType()), "workType must be provided.");
        checkArgument(isNotEmpty(requestV2.latitude()), "latitude must be provided.");
        checkArgument(isNotEmpty(requestV2.longitude()), "longitude must be provided.");

        String currentUserId = AuthUtils.getCurrentUserId();
        UserResponse user = userClient.getById(requestV2.userUniqueId());

        checkArgument(StringUtils.equals(currentUserId, user.userId()), "Not match login account and record account");

        AttendanceRecord attendanceRecord =
            attendanceRecordRepository.getWaitingByWorkingDate(user.uniqueId(),
                    LocalDate.now())
                .orElseThrow(() -> new NotFoundException("No exist attendance record."));

        attendanceRecord.clockIn(requestV2.workType(), requestV2.latitude(), requestV2.longitude());
        attendanceRecord.updateClockInTime(LocalDateTime.now());

        log.debug("recorded clock in. ({})", attendanceRecord);

        return from(attendanceRecord);
    }

    @Transactional
    @Override
    public AttendanceRecordResponse clockOut(ClockOutRequest clockOutRequest) {

        ClockOutRequestV2 requestV2 = (ClockOutRequestV2)clockOutRequest;

        checkArgument(StringUtils.isNotBlank(requestV2.userUniqueId()), "userUniqueId must be provided.");
        checkArgument(isNotEmpty(requestV2.attendanceRecordId()), "attendanceRecordId must be provided.");
        checkArgument(isNotEmpty(requestV2.latitude()), "latitude must be provided.");
        checkArgument(isNotEmpty(requestV2.longitude()), "longitude must be provided.");

        AttendanceRecord attendanceRecord = attendanceRecordRepository.findById(requestV2.attendanceRecordId())
            .orElseThrow(() -> new NotFoundException("No exist attendance record."));

        attendanceRecord.clockOut(requestV2.latitude(), requestV2.longitude());
        attendanceRecord.updateClockOutTime(LocalDateTime.now());


        log.debug("recorded clock out. ({})", attendanceRecord);

        return from(attendanceRecord);
    }
}
