package com.malgn.ontimeapi.domain.attendance.provider;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.malgn.common.exception.NotFoundException;
import com.malgn.common.exception.NotSupportException;
import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceType;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceCheckRepository;
import com.malgn.ontimeapi.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
public class DelegatingAttendanceProvider {

    private final List<AttendanceProvider> providers = new ArrayList<>();

    private final AttendanceCheckRepository attendanceCheckRepository;

    public AttendanceRecord recordAttendance(Id<AttendanceCheck, String> checkId,
        Id<UserResponse, String> userUniqueId) {

        AttendanceCheck check =
            attendanceCheckRepository.findById(checkId.getValue())
                .orElseThrow(() -> new NotFoundException(checkId));

        for (AttendanceProvider provider : providers) {
            if (provider.isSupport(check.getAttendanceType())) {
                return provider.recordAttendance(checkId, userUniqueId);
            }

        }

        throw new NotSupportException(check.getAttendanceType().name());
    }

    public void addProvider(AttendanceProvider provider) {
        providers.add(provider);

        log.debug("added attendance provider: {}", provider.getClass().getSimpleName());
    }
}
