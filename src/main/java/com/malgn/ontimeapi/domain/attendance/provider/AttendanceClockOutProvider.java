package com.malgn.ontimeapi.domain.attendance.provider;

import static org.apache.commons.lang3.ObjectUtils.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationEventPublisher;

import com.google.common.collect.Lists;

import com.malgn.common.exception.AlreadyExecuteException;
import com.malgn.common.exception.NotFoundException;
import com.malgn.common.model.Id;
import com.malgn.notification.model.MessageLevel;
import com.malgn.notification.model.SendNotificationMessageBlockRequest;
import com.malgn.notification.model.SendNotificationMessageRequest;
import com.malgn.ontimeapi.common.timecode.Timecode;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceCheck;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceRecord;
import com.malgn.ontimeapi.domain.attendance.entity.AttendanceType;
import com.malgn.ontimeapi.domain.attendance.event.SendMessage;
import com.malgn.ontimeapi.domain.attendance.exception.ExpiredException;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceCheckRepository;
import com.malgn.ontimeapi.domain.attendance.repository.AttendanceRecordRepository;
import com.malgn.ontimeapi.domain.position.entity.Position;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.entity.TeamUser;
import com.malgn.ontimeapi.domain.team.repository.TeamUserRepository;
import com.malgn.ontimeapi.domain.user.entity.UserPosition;
import com.malgn.ontimeapi.domain.user.feign.UserFeignClient;
import com.malgn.ontimeapi.domain.user.model.UserResponse;
import com.malgn.ontimeapi.domain.user.repository.UserPositionRepository;

@Slf4j
public class AttendanceClockOutProvider implements AttendanceProvider {

    private static final String DISPLAY_MESSAGE_TEMPLATE = "%s - %s 퇴근하였습니다.";

    private static final DateTimeFormatter DEFAULT_FORMAT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd (E)");
    private static final DateTimeFormatter DEFAULT_FORMAT_TIME = DateTimeFormatter.ofPattern("a hh:mm:ss");

    private final ApplicationEventPublisher publisher;

    private final AttendanceCheckRepository checkRepository;
    private final AttendanceRecordRepository recordRepository;
    private final TeamUserRepository teamUserRepository;
    private final UserPositionRepository userPositionRepository;

    private final UserFeignClient userClient;

    @Builder
    private AttendanceClockOutProvider(ApplicationEventPublisher publisher, AttendanceCheckRepository checkRepository,
        AttendanceRecordRepository recordRepository, TeamUserRepository teamUserRepository,
        UserPositionRepository userPositionRepository, UserFeignClient userClient) {
        this.publisher = publisher;
        this.checkRepository = checkRepository;
        this.recordRepository = recordRepository;
        this.teamUserRepository = teamUserRepository;
        this.userPositionRepository = userPositionRepository;
        this.userClient = userClient;
    }

    @Override
    public AttendanceRecord recordAttendance(Id<AttendanceCheck, String> checkId,
        Id<UserResponse, String> userUniqueId) {

        AttendanceCheck check =
            checkRepository.findById(checkId.getValue())
                .orElseThrow(() -> new NotFoundException(checkId));

        LocalDateTime now = LocalDateTime.now();

        // expired check
        if (check.getExpiredDate().isBefore(now)) {
            throw new ExpiredException("expired attendance check.");
        }

        AttendanceRecord attendanceRecord =
            recordRepository.getClockInByWorkingDate(userUniqueId.getValue(),
                    check.getWorkingDate())
                .orElseThrow(() -> new NotFoundException("record clock in..."));

        if (isNotEmpty(attendanceRecord.getClockOutTime())) {
            throw new AlreadyExecuteException();
        }

        attendanceRecord.updateClockOutTime(now);

        log.debug("clock out user. (userUniqueId={}, time={})", userUniqueId.getValue(), now);

        TeamUser teamUser =
            teamUserRepository.getTeamByUser(attendanceRecord.getUserUniqueId())
                .orElseThrow(() -> new NotFoundException(TeamUser.class, attendanceRecord.getUserUniqueId()));

        String teamUserMessage = parseTeamUserMessage(teamUser);
        String dateMessage = attendanceRecord.getWorkingDate().format(DEFAULT_FORMAT_DATE);

        boolean isCheck = attendanceRecord.getLeaveWorkAt().isBefore(attendanceRecord.getClockOutTime());

        SendNotificationMessageRequest message =
            SendNotificationMessageRequest.builder()
                .level(isCheck ? MessageLevel.INFO : MessageLevel.WARING)
                .displayMessage(String.format(DISPLAY_MESSAGE_TEMPLATE, teamUserMessage, dateMessage))
                .fields(parseBlockMessages(attendanceRecord))
                .build();

        publisher.publishEvent(
            SendMessage.builder()
                .teamId(Id.of(Team.class, teamUser.getTeam().getId()))
                .sendRequest(message)
                .build());

        return attendanceRecord;
    }

    @Override
    public boolean isSupport(AttendanceType type) {
        return type == AttendanceType.CLOCK_OUT;
    }

    private String parseTeamUserMessage(TeamUser teamUser) {

        StringBuilder messageBuilder = new StringBuilder();

        UserPosition userPosition =
            userPositionRepository.getUserPosition(teamUser.getUserUniqueId())
                .orElseThrow(() -> new NotFoundException(UserPosition.class, teamUser.getUserUniqueId()));

        UserResponse user = userClient.getById(teamUser.getUserUniqueId());
        Team team = teamUser.getTeam();
        Position position = userPosition.getPosition();

        // add team
        messageBuilder.append(team.getName());

        if (teamUser.isLeader()) {
            messageBuilder.append("(").append("팀장").append(") ");
        }

        messageBuilder.append(user.username()).append(" ").append(position.getName());

        return messageBuilder.toString();
    }

    private List<SendNotificationMessageBlockRequest> parseBlockMessages(AttendanceRecord attendanceRecord) {

        List<SendNotificationMessageBlockRequest> blocks = Lists.newArrayList();

        blocks.add(
            SendNotificationMessageBlockRequest.builder()
                .field("근무일")
                .text(attendanceRecord.getWorkingDate().format(DEFAULT_FORMAT_DATE))
                .build());

        blocks.add(
            SendNotificationMessageBlockRequest.builder()
                .field("출근 시간")
                .text(attendanceRecord.getClockInTime().format(DEFAULT_FORMAT_TIME))
                .build());

        blocks.add(
            SendNotificationMessageBlockRequest.builder()
                .field("퇴근 예정 시간")
                .text(attendanceRecord.getLeaveWorkAt().format(DEFAULT_FORMAT_TIME))
                .build());

        blocks.add(
            SendNotificationMessageBlockRequest.builder()
                .field("퇴근 시간")
                .text(attendanceRecord.getClockOutTime().format(DEFAULT_FORMAT_TIME))
                .build());

        Duration workDuration = Duration.between(attendanceRecord.getClockInTime(), attendanceRecord.getClockOutTime());
        Timecode workTimecode = new Timecode(workDuration.getSeconds());

        blocks.add(
            SendNotificationMessageBlockRequest.builder()
                .field("총 근무 시간")
                .text(workTimecode.toString())
                .build());

        boolean isCheck = attendanceRecord.getLeaveWorkAt().isBefore(attendanceRecord.getClockOutTime());

        blocks.add(
            SendNotificationMessageBlockRequest.builder()
                .field("근무 자세")
                .text(isCheck ? "정상" : "경고")
                .build());

        return blocks;
    }
}
