package com.malgn.ontimeapi.domain.attendance.provider;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.malgn.common.exception.AlreadyExistException;
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
public class AttendanceClockInProvider implements AttendanceProvider {

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

        AttendanceRecord prevRecord =
            recordRepository.getClockInByWorkingDate(userUniqueId.getValue(), check.getWorkingDate())
                .orElse(null);

        if (prevRecord != null) {
            throw new AlreadyExistException("exist clock in.");
        }

        AttendanceRecord attendanceRecord =
            recordRepository.getWaitingByWorkingDate(userUniqueId.getValue(),
                    check.getWorkingDate())
                .orElseThrow(() -> new NotFoundException("No exist waiting attendance record..."));

        attendanceRecord.updateClockInTime(now);

        log.debug("clock in user. (userUniqueId={}, time={})", userUniqueId.getValue(), now);

        return attendanceRecord;
    }

    @Override
    public boolean isSupport(AttendanceType type) {
        return type == AttendanceType.CLOCK_IN;
    }
}
