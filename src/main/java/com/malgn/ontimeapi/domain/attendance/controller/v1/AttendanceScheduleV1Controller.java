package com.malgn.ontimeapi.domain.attendance.controller.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontimeapi.domain.attendance.model.v1.CreateAttendanceScheduleV1Request;
import com.malgn.ontimeapi.domain.attendance.service.v1.AttendanceScheduleV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/attendance/schedules")
public class AttendanceScheduleV1Controller {

    private final AttendanceScheduleV1Service attendanceScheduleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public AttendanceRecordResponse createRecord(@RequestBody CreateAttendanceScheduleV1Request createdRecord) {
        return attendanceScheduleService.createSchedule(createdRecord);
    }
}
