package com.malgn.ontimeapi.domain.attendance.event.listener;

import static org.apache.commons.lang3.ObjectUtils.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.notification.client.NotificationClient;
import com.malgn.ontimeapi.domain.attendance.event.SendMessage;
import com.malgn.ontimeapi.domain.team.entity.TeamUser;
import com.malgn.ontimeapi.domain.team.repository.TeamUserRepository;
import com.malgn.ontimeapi.domain.user.entity.UserNotificationProvider;
import com.malgn.ontimeapi.domain.user.repository.UserNotificationProviderRepository;

@Slf4j
@RequiredArgsConstructor
@Component
@Transactional(readOnly = true)
public class SendMessageToLeaderEventListener {

    private final NotificationClient notificationClient;

    private final TeamUserRepository teamUserRepository;
    private final UserNotificationProviderRepository notificationProviderRepository;

    @EventListener
    public void send(SendMessage message) {

        List<TeamUser> leaders = teamUserRepository.getLeaders(message.teamId());

        for (TeamUser leader : leaders) {
            UserNotificationProvider provider =
                notificationProviderRepository.getProvider(leader.getUserUniqueId())
                    .orElse(null);

            if (isEmpty(provider)) {
                continue;
            }

            notificationClient.send(provider.getProviderId(), message.sendRequest());
        }

    }

}
