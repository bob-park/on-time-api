package com.malgn.ontimeapi.domain.attendance.controller.v1;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontimeapi.domain.attendance.model.v1.AttendanceRecordV1Request;
import com.malgn.ontimeapi.domain.attendance.model.v1.GetAttendanceRecordV1Request;
import com.malgn.ontimeapi.domain.attendance.service.v1.AttendanceRecordV1Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/attendance/records")
public class AttendanceRecordV1Controller {

    private final AttendanceRecordV1Service recordService;

    @PostMapping(path = "")
    public AttendanceRecordResponse recordAttendance(@RequestBody AttendanceRecordV1Request recordRequest) {
        return recordService.recordAttendance(recordRequest);
    }

    @GetMapping(path = "")
    public List<AttendanceRecordResponse> getRecords(GetAttendanceRecordV1Request getRequest){
        return recordService.getRecords(getRequest);
    }
}
