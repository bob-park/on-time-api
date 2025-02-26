package com.malgn.ontimeapi.domain.attendance.controller.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceCheckResponse;
import com.malgn.ontimeapi.domain.attendance.model.v1.CurrentV1Request;
import com.malgn.ontimeapi.domain.attendance.service.v1.AttendanceCheckV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/attendance/check")
public class AttendanceCheckV1Controller {

    private final AttendanceCheckV1Service attendanceCheckService;

    @PostMapping(path = "current")
    public AttendanceCheckResponse currentAttendanceCheck(@RequestBody CurrentV1Request currentRequest) {
        return attendanceCheckService.currentCheck(currentRequest);
    }

}
