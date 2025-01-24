package com.malgn.ontimeapi.domain.attendance.service;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordRequest;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;

public interface AttendanceRecordService {

    AttendanceRecordResponse recordAttendance(AttendanceRecordRequest recordRequest);

}
