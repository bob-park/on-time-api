package com.malgn.ontimeapi.configure.ontime.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("on-time")
public record OnTimeProperties(@NestedConfigurationProperty AttendanceProperties attendance) {
}
