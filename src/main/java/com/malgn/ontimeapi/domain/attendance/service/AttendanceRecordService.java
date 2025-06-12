package com.malgn.ontimeapi.domain.attendance.service;

import java.util.List;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordRequest;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontimeapi.domain.attendance.model.ClockInRequest;
import com.malgn.ontimeapi.domain.attendance.model.ClockOutRequest;
import com.malgn.ontimeapi.domain.attendance.model.GetAttendanceRecordRequest;

public interface AttendanceRecordService {

    default AttendanceRecordResponse recordAttendance(AttendanceRecordRequest recordRequest) {
        return null;
    }

    List<AttendanceRecordResponse> getRecords(GetAttendanceRecordRequest getRequest);

    default AttendanceRecordResponse clockIn(ClockInRequest clockInRequest) {
        return null;
    }

    default AttendanceRecordResponse clockOut(ClockOutRequest clockOutRequest) {
        return null;
    }

}
