package com.malgn.ontimeapi.domain.attendance.service.v1;

import static com.google.common.base.Preconditions.*;
import static com.malgn.ontimeapi.domain.attendance.model.v1.AttendanceGpsV1Response.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.common.exception.NotFoundException;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceGps;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceGpsResponse;
import com.malgn.ontimeapi.domain.attendance.model.SearchAttendanceGpsRequest;
import com.malgn.ontimeapi.domain.attendance.model.v1.AttendanceGpsV1Response;
import com.malgn.ontimeapi.domain.attendance.model.v1.SearchAttendanceGpsV1Request;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceGpsRepository;
import com.malgn.ontimeapi.domain.attendance.service.AttendanceGpsService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AttendanceGpsV1Service implements AttendanceGpsService {

    private final AttendanceGpsRepository attendanceGpsRepository;

    @Override
    public List<AttendanceGpsResponse> getAll() {

        List<AttendanceGps> result = attendanceGpsRepository.getAllGps();

        return result.stream()
            .map(AttendanceGpsV1Response::from)
            .toList();
    }


}
