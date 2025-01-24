package com.malgn.ontimeapi.domain.attendance.provider;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.malgn.common.exception.NotFoundException;
import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceType;
import com.malgn.ontimeapi.domain.attendance.exception.ExpiredException;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceCheckRepository;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceRecordRepository;
import com.malgn.ontimeapi.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
public class AttendanceClockOutProvider implements AttendanceProvider {

    private final AttendanceCheckRepository checkRepository;
    private final AttendanceRecordRepository recordRepository;

    @Override
    public AttendanceRecord recordAttendance(Id<AttendanceCheck, String> checkId,
        Id<UserResponse, String> userUniqueId) {

        AttendanceCheck check =
            checkRepository.findById(checkId.getValue())
                .orElseThrow(() -> new NotFoundException(checkId));

        LocalDateTime now = LocalDateTime.now();

        // expired check
        if (check.getExpiredDate().isBefore(now)) {
            throw new ExpiredException("expired attendance check.");
        }

        AttendanceRecord attendanceRecord =
            recordRepository.getClockInByWorkingDate(userUniqueId.getValue(),
                    check.getWorkingDate())
                .orElseThrow(() -> new NotFoundException("record clock in..."));

        attendanceRecord.updateClockOutTime(now);

        log.debug("clock out user. (userUniqueId={}, time={})", userUniqueId.getValue(), now);

        return attendanceRecord;
    }

    @Override
    public boolean isSupport(AttendanceType type) {
        return type == AttendanceType.CLOCK_OUT;
    }
}
