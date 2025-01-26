package com.malgn.ontimeapi.domain.attendance.event.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.malgn.notification.client.NotificationClient;

@Slf4j
@RequiredArgsConstructor
@Component
public class SendMessageToLeaderEventListener {

    private final NotificationClient notificationClient;

    @EventListener
    public void send(SendMessage message) {

        long providerId = 0;

        notificationClient.send(providerId, message.message());
    }
}
