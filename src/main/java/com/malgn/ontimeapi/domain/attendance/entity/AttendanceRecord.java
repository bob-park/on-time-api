package com.malgn.ontimeapi.domain.attendance.entity;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import com.malgn.common.exception.NotSupportException;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "attendances_records")
public class AttendanceRecord extends BaseEntity<Long> {

    private static final int HOURS_DAY_ALL = 8;
    private static final int HOURS_HALF_DAY = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userUniqueId;

    private LocalDate workingDate;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @Enumerated(EnumType.STRING)
    private DayOffType dayOffType;

    private LocalDateTime clockInTime;
    private LocalDateTime leaveWorkAt;
    private LocalDateTime clockOutTime;

    private String message;

    @Builder
    private AttendanceRecord(Long id, String userUniqueId, LocalDate workingDate, AttendanceStatus status,
        DayOffType dayOffType, String message) {

        checkArgument(StringUtils.isNotBlank(userUniqueId), "userUniqueId must be provided.");
        checkArgument(isNotEmpty(workingDate), "workingDate must be provided.");

        this.id = id;
        this.status = defaultIfNull(status, AttendanceStatus.WAITING);
        this.dayOffType = dayOffType;
        this.userUniqueId = userUniqueId;
        this.workingDate = workingDate;
        this.message = message;
    }

    /*
     * 편의 메서드
     */
    public void updateClockInTime(LocalDateTime clockInTime) {
        this.clockInTime = clockInTime;
        this.leaveWorkAt = calculateLeaveWorkAt(clockInTime);
    }

    public void updateClockOutTime(LocalDateTime clockOutTime) {
        this.clockOutTime = clockOutTime;
        this.status = getClockOutTime().isAfter(getLeaveWorkAt()) ? AttendanceStatus.SUCCESS : AttendanceStatus.WARNING;
    }

    private LocalDateTime calculateLeaveWorkAt(LocalDateTime clockInTime) {

        boolean isAm = clockInTime.toLocalTime().isBefore(LocalTime.NOON);

        // 점심시간 포함 여부
        int plusHours = isAm ? 1 : 0;

        switch (getDayOffType()) {
            case null -> plusHours += HOURS_DAY_ALL;
            case AM_HALF_DAY_OFF,PM_HALF_DAY_OFF -> plusHours += HOURS_HALF_DAY;
            default -> throw new NotSupportException(dayOffType.name());
        }

        int minute = clockInTime.getMinute();
        int tempMinute = minute % 10;

        int plusMinutes = 0;

        if (tempMinute > 0) {
            plusMinutes = ((minute / 10) + 1) * 10;
        }

        return clockInTime.toLocalDate()
            .atTime(clockInTime.getHour(), 0, 0)
            .plusHours(plusHours).plusMinutes(plusMinutes);
    }

}
