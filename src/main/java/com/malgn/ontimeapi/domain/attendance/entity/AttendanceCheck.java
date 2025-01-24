package com.malgn.ontimeapi.domain.attendance.entity;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.malgn.common.entity.BaseEntity;
import com.malgn.common.entity.annotation.BaseIdGenerateValue;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "attendances_checks")
public class AttendanceCheck extends BaseEntity<String> {

    @Id
    @BaseIdGenerateValue
    private String id;

    @Enumerated(EnumType.STRING)
    private AttendanceCheckType type;

    private LocalDate workingDate;
    private LocalDateTime expiredDate;

    @Builder
    private AttendanceCheck(String id, AttendanceCheckType type, LocalDate workingDate, LocalDateTime expiredDate) {

        checkArgument(isNotEmpty(type), "type must be provided.");
        checkArgument(isNotEmpty(workingDate), "workingDate must be provided.");
        checkArgument(isNotEmpty(expiredDate), "expiredDate must be provided.");

        this.id = id;
        this.type = type;
        this.workingDate = workingDate;
        this.expiredDate = expiredDate;
    }
}
