package com.malgn.ontimeapi.domain.user.entity;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users_notification_providers")
public class UserNotificationProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userUniqueId;

    private Long providerId;

    @Builder
    private UserNotificationProvider(Long id, String userUniqueId, Long providerId) {

        checkArgument(StringUtils.isNotBlank(userUniqueId),"userUniqueId must be provided.");
        checkArgument(isNotEmpty(providerId),"providerId must be provided.");

        this.id = id;
        this.userUniqueId = userUniqueId;
        this.providerId = providerId;
    }
}
