package com.malgn.ontimeapi.domain.attendance.repository.query;

import java.time.LocalDateTime;
import java.util.Optional;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;

public interface AttendanceCheckQueryRepository {

    Optional<AttendanceCheck> getBetweenDateTime(LocalDateTime current);

}
