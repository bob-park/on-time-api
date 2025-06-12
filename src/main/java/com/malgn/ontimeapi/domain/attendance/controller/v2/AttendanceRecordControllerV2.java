package com.malgn.ontimeapi.domain.attendance.controller.v2;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontimeapi.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontimeapi.domain.attendance.model.v2.ClockInRequestV2;
import com.malgn.ontimeapi.domain.attendance.model.v2.ClockOutRequestV2;
import com.malgn.ontimeapi.domain.attendance.model.v2.GetAttendanceRecordRequestV2;
import com.malgn.ontimeapi.domain.attendance.service.v2.AttendanceRecordServiceV2;

@RequiredArgsConstructor
@RestController
@RequestMapping("v2/attendance/records")
public class AttendanceRecordControllerV2 {

    private final AttendanceRecordServiceV2 recordService;

    @GetMapping(path = "")
    public List<AttendanceRecordResponse> getRecords(GetAttendanceRecordRequestV2 getRequest) {
        return recordService.getRecords(getRequest);
    }

    @PostMapping(path = "clock/in")
    public AttendanceRecordResponse clockIn(@RequestBody ClockInRequestV2 clockInRequest) {
        return recordService.clockIn(clockInRequest);
    }

    @PostMapping(path = "clock/out")
    public AttendanceRecordResponse clockOut(@RequestBody ClockOutRequestV2 clockOutRequest) {
        return recordService.clockOut(clockOutRequest);
    }

}
