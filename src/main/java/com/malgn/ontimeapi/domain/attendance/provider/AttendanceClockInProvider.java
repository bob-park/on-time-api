package com.malgn.ontimeapi.domain.attendance.provider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationEventPublisher;

import com.malgn.common.exception.AlreadyExistException;
import com.malgn.common.exception.NotFoundException;
import com.malgn.common.exception.NotSupportException;
import com.malgn.common.model.Id;
import com.malgn.notification.client.NotificationClient;
import com.malgn.notification.model.SendNotificationMessageRequest;
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
public class AttendanceClockInProvider implements AttendanceProvider {

    private static final String DISPLAY_MESSAGE_TEMPLATE = "%s - %s 출근하였습니다.";

    private static final DateTimeFormatter DEFAULT_FORMAT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd (E)");
    private static final DateTimeFormatter DEFAULT_FORMAT_DATETIME =
        DateTimeFormatter.ofPattern("yyyy-MM-dd (E) HH:mm:ss");

    private final ApplicationEventPublisher publisher;

    private final AttendanceCheckRepository checkRepository;
    private final AttendanceRecordRepository recordRepository;
    private final TeamUserRepository teamUserRepository;
    private final UserPositionRepository userPositionRepository;

    private final UserFeignClient userClient;

    @Builder
    private AttendanceClockInProvider(ApplicationEventPublisher publisher, AttendanceCheckRepository checkRepository,
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

        AttendanceRecord prevRecord =
            recordRepository.getClockInByWorkingDate(userUniqueId.getValue(), check.getWorkingDate())
                .orElse(null);

        if (prevRecord != null) {
            throw new AlreadyExistException("exist clock in.");
        }

        AttendanceRecord attendanceRecord =
            recordRepository.getWaitingByWorkingDate(userUniqueId.getValue(),
                    check.getWorkingDate())
                .orElseThrow(() -> new NotFoundException("No exist waiting attendance record..."));

        attendanceRecord.updateClockInTime(now);

        log.debug("clock in user. (userUniqueId={}, time={})", userUniqueId.getValue(), now);

        TeamUser teamUser =
            teamUserRepository.getTeamByUser(attendanceRecord.getUserUniqueId())
                .orElseThrow(() -> new NotFoundException(TeamUser.class, attendanceRecord.getUserUniqueId()));

        String teamUserMessage = parseTeamUserMessage(teamUser);
        String dateMessage = attendanceRecord.getWorkingDate().format(DEFAULT_FORMAT_DATE);

        SendNotificationMessageRequest message =
            SendNotificationMessageRequest.builder()
                .displayMessage(String.format(DISPLAY_MESSAGE_TEMPLATE, teamUserMessage, dateMessage))
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
        return type == AttendanceType.CLOCK_IN;
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
}
