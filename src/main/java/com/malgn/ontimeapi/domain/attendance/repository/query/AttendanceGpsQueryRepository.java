package com.malgn.ontimeapi.domain.attendance.repository.query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceGps;

public interface AttendanceGpsQueryRepository {

    List<AttendanceGps> getAllGps();

    Optional<AttendanceGps> getGps(BigDecimal latitude, BigDecimal longitude);


}
