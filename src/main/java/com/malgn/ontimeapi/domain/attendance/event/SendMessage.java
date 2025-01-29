package com.malgn.ontimeapi.domain.attendance.event;

import lombok.Builder;

import com.malgn.common.model.Id;
import com.malgn.notification.model.SendNotificationMessageRequest;
import com.malgn.ontimeapi.domain.team.entity.Team;

@Builder
public record SendMessage(Id<Team, Long> teamId,
                          SendNotificationMessageRequest sendRequest) {
}
