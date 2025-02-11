package com.malgn.ontimeapi.domain.attendance.entity;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;

import com.malgn.common.entity.BaseEntity;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "attendances_gps")
public class AttendanceGps extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private BigDecimal latitude;
    private BigDecimal longitude;

    @Builder
    private AttendanceGps(Long id, String name, String description, BigDecimal latitude, BigDecimal longitude) {

        checkArgument(StringUtils.isNotBlank(name), "name must be provided.");
        checkArgument(isNotEmpty(latitude), "latitude must be provided.");
        checkArgument(isNotEmpty(longitude), "longitude must be provided.");

        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
