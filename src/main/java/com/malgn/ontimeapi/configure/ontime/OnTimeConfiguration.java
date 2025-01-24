package com.malgn.ontimeapi.configure.ontime;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.malgn.ontimeapi.configure.ontime.properties.OnTimeProperties;
import com.malgn.ontimeapi.domain.attendance.provider.AttendanceClockInProvider;
import com.malgn.ontimeapi.domain.attendance.provider.AttendanceClockOutProvider;
import com.malgn.ontimeapi.domain.attendance.provider.DelegatingAttendanceProvider;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceCheckRepository;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceRecordRepository;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(OnTimeProperties.class)
public class OnTimeConfiguration {

    private final OnTimeProperties properties;

    private final AttendanceCheckRepository checkRepository;
    private final AttendanceRecordRepository recordRepository;

    @Bean
    public DelegatingAttendanceProvider attendanceProvider() {
        DelegatingAttendanceProvider provider = new DelegatingAttendanceProvider(checkRepository);

        provider.addProvider(new AttendanceClockInProvider(checkRepository, recordRepository));
        provider.addProvider(new AttendanceClockOutProvider(checkRepository, recordRepository));

        return provider;
    }
}
