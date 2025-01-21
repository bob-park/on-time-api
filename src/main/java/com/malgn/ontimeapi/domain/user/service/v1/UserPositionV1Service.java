package com.malgn.ontimeapi.domain.user.service.v1;

import static com.malgn.ontimeapi.domain.user.model.v1.UserPositionV1Response.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.common.exception.NotFoundException;
import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.user.entity.UserPosition;
import com.malgn.ontimeapi.domain.user.model.UserPositionResponse;
import com.malgn.ontimeapi.domain.user.model.v1.UserPositionV1Response;
import com.malgn.ontimeapi.domain.user.repository.UserPositionRepository;
import com.malgn.ontimeapi.domain.user.service.UserPositionService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserPositionV1Service implements UserPositionService {

    private final UserPositionRepository userPositionRepository;

    @Override
    public UserPositionResponse getUserPosition(Id<UserPosition, String> userId) {

        UserPosition userPosition =
            userPositionRepository.getUserPosition(userId.getValue())
                .orElseThrow(() -> new NotFoundException(userId));

        return from(userId.getValue(), userPosition.getPosition());
    }
}
