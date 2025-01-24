package com.malgn.ontimeapi.domain.attendance.service;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceCheckResponse;
import com.malgn.ontimeapi.domain.attendance.model.CurrentRequest;

public interface AttendanceCheckService {

    AttendanceCheckResponse currentCheck(CurrentRequest currentRequest);

    AttendanceCheckResponse getCheck(Id<AttendanceCheck, String> attendanceCheckId);
}
