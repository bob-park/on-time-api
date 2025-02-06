package com.malgn.ontimeapi.domain.attendance.service;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontimeapi.domain.attendance.model.CreateAttendanceScheduleRequest;

public interface AttendanceScheduleService {

    AttendanceRecordResponse createSchedule(CreateAttendanceScheduleRequest createRequest);

}
