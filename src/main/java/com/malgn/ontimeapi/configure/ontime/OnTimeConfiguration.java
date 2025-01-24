package com.malgn.ontimeapi.configure.ontime;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.malgn.ontimeapi.configure.ontime.properties.OnTimeProperties;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(OnTimeProperties.class)
public class OnTimeConfiguration {

    private final OnTimeProperties onTimeProperties;
}
