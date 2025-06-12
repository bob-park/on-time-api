package com.malgn.ontimeapi.configure.ontime;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.malgn.ontimeapi.configure.ontime.properties.OnTimeProperties;
import com.malgn.ontimeapi.domain.attendance.provider.DelegatingAttendanceProvider;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceCheckRepository;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceRecordRepository;
import com.malgn.ontimeapi.domain.user.feign.UserFeignClient;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(OnTimeProperties.class)
public class OnTimeConfiguration {

    private final OnTimeProperties properties;

    private final ApplicationEventPublisher publisher;

    private final UserFeignClient userClient;

    private final AttendanceCheckRepository checkRepository;
    private final AttendanceRecordRepository recordRepository;

    @Bean
    public DelegatingAttendanceProvider attendanceProvider() {
        DelegatingAttendanceProvider provider = new DelegatingAttendanceProvider(checkRepository);

        return provider;
    }
}
