package com.malgn.ontimeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class OnTimeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnTimeApiApplication.class, args);
    }

}
