package com.malgn.ontimeapi.domain.attendance.service.v1;

import static com.google.common.base.Preconditions.*;
import static com.malgn.ontimeapi.domain.attendance.model.v1.AttendanceRecordV1Response.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang3.StringUtils;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordRequest;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontimeapi.domain.attendance.model.GetAttendanceRecordRequest;
import com.malgn.ontimeapi.domain.attendance.model.v1.AttendanceRecordV1Request;
import com.malgn.ontimeapi.domain.attendance.model.v1.AttendanceRecordV1Response;
import com.malgn.ontimeapi.domain.attendance.model.v1.GetAttendanceRecordV1Request;
import com.malgn.ontimeapi.domain.attendance.provider.DelegatingAttendanceProvider;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceGpsRepository;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceRecordRepository;
import com.malgn.ontimeapi.domain.attendance.service.AttendanceRecordService;
import com.malgn.ontimeapi.domain.user.feign.UserFeignClient;
import com.malgn.ontimeapi.domain.user.model.UserResponse;
import com.malgn.ontimeapi.utils.AuthUtils;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AttendanceRecordV1Service implements AttendanceRecordService {

    private final UserFeignClient userClient;

    private final DelegatingAttendanceProvider provider;
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final AttendanceGpsRepository attendanceGpsRepository;

    @Transactional
    @Override
    public AttendanceRecordResponse recordAttendance(AttendanceRecordRequest recordRequest) {

        AttendanceRecordV1Request recordV1Request = (AttendanceRecordV1Request)recordRequest;

        checkArgument(StringUtils.isNotBlank(recordV1Request.checkId()), "checkId must be provided.");
        checkArgument(StringUtils.isNotBlank(recordV1Request.userUniqueId()), "userUniqueId must be provided.");

        String currentUserId = AuthUtils.getCurrentUserId();
        UserResponse user = userClient.getById(recordV1Request.userUniqueId());

        checkArgument(StringUtils.equals(currentUserId, user.userId()), "Not match login account and record account");

        AttendanceRecord attendanceRecord =
            provider.recordAttendance(
                Id.of(AttendanceCheck.class, recordV1Request.checkId()),
                Id.of(UserResponse.class, recordV1Request.userUniqueId()));

        return from(attendanceRecord);
    }


    @Override
    public List<AttendanceRecordResponse> getRecords(GetAttendanceRecordRequest getRequest) {

        GetAttendanceRecordV1Request getV1Request = (GetAttendanceRecordV1Request)getRequest;

        checkArgument(StringUtils.isNotBlank(getV1Request.userUniqueId()), "userUniqueId must be provided.");

        List<AttendanceRecord> result = attendanceRecordRepository.getAllRecords(getV1Request);

        return result.stream()
            .map(AttendanceRecordV1Response::from)
            .toList();
    }

    private BigDecimal round(BigDecimal value) {
        return value.setScale(5, RoundingMode.HALF_UP);
    }

}
