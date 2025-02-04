package com.malgn.ontimeapi.domain.attendance.model.v1;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.ontimeapi.domain.attendance.entity.AttendanceGps;
import com.malgn.ontimeapi.domain.attendance.model.AttendanceGpsResponse;

@Builder
public record AttendanceGpsV1Response(Long id,
                                      String name,
                                      String description,
                                      BigDecimal latitude,
                                      BigDecimal longitude,
                                      LocalDateTime createdDate,
                                      String createdBy,
                                      LocalDateTime lastModifiedDate,
                                      String lastModifiedBy)
    implements AttendanceGpsResponse {

    public static AttendanceGpsResponse from(AttendanceGps gps) {
        return AttendanceGpsV1Response.builder()
            .id(gps.getId())
            .name(gps.getName())
            .description(gps.getDescription())
            .latitude(gps.getLatitude())
            .longitude(gps.getLongitude())
            .createdDate(gps.getCreatedDate())
            .createdBy(gps.getCreatedBy())
            .lastModifiedDate(gps.getLastModifiedDate())
            .lastModifiedBy(gps.getLastModifiedBy())
            .build();
    }
}
