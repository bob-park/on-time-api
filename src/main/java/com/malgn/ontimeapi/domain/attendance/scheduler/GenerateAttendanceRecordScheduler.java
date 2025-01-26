package com.malgn.ontimeapi.domain.attendance.scheduler;

import java.time.LocalDate;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceRecordRepository;
import com.malgn.ontimeapi.domain.user.feign.UserFeignClient;
import com.malgn.ontimeapi.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Component
@Transactional(readOnly = true)
public class GenerateAttendanceRecordScheduler {

    private final AttendanceRecordRepository recordRepository;

    private final UserFeignClient userClient;

    @Transactional
    @Scheduled(cron = "${on-time.attendance.schedule.cron-generate-attendance-record}")
    public void generateAttendanceRecord() {
        SimplePageImpl<UserResponse> result = userClient.getAll(PageRequest.of(0, 1_000));

        List<UserResponse> users = result.content();

        LocalDate now = LocalDate.now();

        for (UserResponse user : users) {

            boolean isExist = recordRepository.existRecord(user.uniqueId(), now);

            if (isExist) {
                continue;
            }

            // TODO 연(월)차 연동 필요
            AttendanceRecord createdRecord =
                AttendanceRecord.builder()
                    .userUniqueId(user.uniqueId())
                    .workingDate(now)
                    .build();

            createdRecord = recordRepository.save(createdRecord);

            log.debug("created attendance record: {}", createdRecord);

        }
    }

}
