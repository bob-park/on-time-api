package com.malgn.ontimeapi.domain.attendance.provider;

import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceType;
import com.malgn.ontimeapi.domain.user.model.UserResponse;

public interface AttendanceProvider {

    AttendanceRecord recordAttendance(Id<AttendanceCheck, String> checkId, Id<UserResponse, String> userUniqueId);

    default boolean isSupport(AttendanceType type) {
        return false;
    }
}
