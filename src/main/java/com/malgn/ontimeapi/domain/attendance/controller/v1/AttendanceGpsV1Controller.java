package com.malgn.ontimeapi.domain.attendance.controller.v1;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceGpsResponse;
import com.malgn.ontimeapi.domain.attendance.service.v1.AttendanceGpsV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/attendance/gps")
public class AttendanceGpsV1Controller {

    private final AttendanceGpsV1Service attendanceGpsService;

    @GetMapping(path = "")
    public List<AttendanceGpsResponse> getAll() {
        return attendanceGpsService.getAll();
    }
}
