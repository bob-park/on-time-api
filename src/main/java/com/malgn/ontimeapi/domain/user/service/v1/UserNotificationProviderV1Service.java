package com.malgn.ontimeapi.domain.user.service.v1;

import static com.malgn.ontimeapi.domain.user.model.v1.UserNotificationProviderV1Response.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.common.exception.AlreadyExistException;
import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.user.entity.UserNotificationProvider;
import com.malgn.ontimeapi.domain.user.model.AddNotificationProviderRequest;
import com.malgn.ontimeapi.domain.user.model.UserNotificationProviderResponse;
import com.malgn.ontimeapi.domain.user.model.UserResponse;
import com.malgn.ontimeapi.domain.user.model.v1.AddNotificationProviderV1Request;
import com.malgn.ontimeapi.domain.user.model.v1.UserNotificationProviderV1Response;
import com.malgn.ontimeapi.domain.user.repository.UserNotificationProviderRepository;
import com.malgn.ontimeapi.domain.user.service.UserNotificationProviderService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserNotificationProviderV1Service implements UserNotificationProviderService {

    private final UserNotificationProviderRepository userNotificationProviderRepository;

    @Transactional
    @Override
    public UserNotificationProviderResponse addProvider(Id<UserResponse, String> userUnqiueId,
        AddNotificationProviderRequest addRequest) {

        AddNotificationProviderV1Request addV1Request = (AddNotificationProviderV1Request)addRequest;

        UserNotificationProvider preProvider =
            userNotificationProviderRepository.getProvider(userUnqiueId.getValue())
                .orElse(null);

        if (isNotEmpty(preProvider)) {
            throw new AlreadyExistException("Already exists notification provider.");
        }

        UserNotificationProvider createdProvider =
            UserNotificationProvider.builder()
                .userUniqueId(userUnqiueId.getValue())
                .providerId(addV1Request.providerId())
                .build();

        createdProvider = userNotificationProviderRepository.save(createdProvider);

        log.debug("added notification provider: {}", createdProvider);

        return from(createdProvider);
    }

    @Transactional
    @Override
    public UserNotificationProviderResponse removeProvider(Id<UserResponse, String> userUnqiueId) {

        userNotificationProviderRepository.deleteProvider(userUnqiueId.getValue());

        return UserNotificationProviderV1Response.builder()
            .userUniqueId(userUnqiueId.getValue())
            .build();
    }
}
