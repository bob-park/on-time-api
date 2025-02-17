package com.malgn.ontimeapi.domain.attendance.entity;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;

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

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "attendances_records")
public class AttendanceRecord extends BaseEntity<Long> {

    private static final int HOURS_DAY_ALL = 8;
    private static final int HOURS_HALF_DAY = 4;

    private static final List<Integer> EXCLUDE_DAY_OF_WEEKS = List.of(6, 7);
    private static final List<Integer> DEFAULT_FAMILY_DAY_WEEKS = List.of(1, 3);

    private static final LocalTime DEFAULT_ALL_DAY_CLOCK_IN_TIME = LocalTime.of(9, 0);
    private static final LocalTime DEFAULT_HALF_DAY_CLOCK_IN_TIME = LocalTime.of(14, 0);

    private static final LocalTime DEFAULT_ALL_DAY_CLOCK_OUT_TIME = LocalTime.of(18, 0);
    private static final LocalTime DEFAULT_PM_HALF_DAY_CLOCK_OUT_TIME = LocalTime.of(14, 0);

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

        int dayOfWeek = getWorkingDate().getDayOfWeek().getValue();
        int weekCountOfMonth = getWorkingDate().get(ChronoField.ALIGNED_WEEK_OF_MONTH);

        // 주말인 경우 적용 제외
        if (EXCLUDE_DAY_OF_WEEKS.contains(dayOfWeek)) {
            return getWorkingDate().atTime(clockInTime.toLocalTime());
        }

        LocalTime calculateLeaveAt = DEFAULT_ALL_DAY_CLOCK_OUT_TIME;

        if (getDayOffType() == DayOffType.PM_HALF_DAY_OFF) {
            calculateLeaveAt = DEFAULT_PM_HALF_DAY_CLOCK_OUT_TIME;
        }

        // family day 적용
        if (getDayOffType() == null && DEFAULT_FAMILY_DAY_WEEKS.contains(weekCountOfMonth) && dayOfWeek == 5) {
            calculateLeaveAt = DEFAULT_PM_HALF_DAY_CLOCK_OUT_TIME;
        }

        // 퇴근해야할 시간 + (출근해야할 시간 - 출근시간)
        LocalTime time = DEFAULT_ALL_DAY_CLOCK_IN_TIME;

        if (getDayOffType() == DayOffType.AM_HALF_DAY_OFF) {
            time = DEFAULT_HALF_DAY_CLOCK_IN_TIME;
        }

        long plusMinutes = 0;

        if (getWorkingDate().atTime(time).isBefore(clockInTime)) {
            Duration between = Duration.between(clockInTime.toLocalTime(), time);

            long minutes = clockInTime.getMinute();

            long betweenSeconds = between.abs().getSeconds();
            long plusHours = betweenSeconds / 3_600 % 24;

            plusMinutes += plusHours * 60;

            if (minutes > 0) {
                plusMinutes += ((minutes / 10) + 1) * 10;
            }

        }

        return getWorkingDate().atTime(calculateLeaveAt)
            .plusMinutes(plusMinutes);
    }

}
