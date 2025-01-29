package com.malgn.ontimeapi.domain.attendance.service;

import java.util.List;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordRequest;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontimeapi.domain.attendance.model.GetAttendanceRecordRequest;

public interface AttendanceRecordService {

    AttendanceRecordResponse recordAttendance(AttendanceRecordRequest recordRequest);

    List<AttendanceRecordResponse> getRecords(GetAttendanceRecordRequest getRequest);

}
