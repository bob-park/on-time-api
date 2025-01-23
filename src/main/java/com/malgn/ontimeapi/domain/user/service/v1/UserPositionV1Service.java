package com.malgn.ontimeapi.domain.user.service.v1;

import static com.google.common.base.Preconditions.*;
import static com.malgn.ontimeapi.domain.user.model.v1.UserPositionV1Response.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.common.exception.NotFoundException;
import com.malgn.ontimeapi.domain.position.entity.Position;
import com.malgn.ontimeapi.domain.position.repository.PositionRepository;
import com.malgn.ontimeapi.domain.user.entity.UserPosition;
import com.malgn.ontimeapi.domain.user.model.UpdateUserPositionRequest;
import com.malgn.ontimeapi.domain.user.model.UserPositionResponse;
import com.malgn.ontimeapi.domain.user.model.v1.UpdateUserPositionV1Request;
import com.malgn.ontimeapi.domain.user.repository.UserPositionRepository;
import com.malgn.ontimeapi.domain.user.service.UserPositionService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserPositionV1Service implements UserPositionService {

    private final UserPositionRepository userPositionRepository;
    private final PositionRepository positionRepository;

    @Transactional
    @Override
    public UserPositionResponse updatePosition(String uniqueUserId, UpdateUserPositionRequest updateRequest) {

        UpdateUserPositionV1Request updateV1Request = (UpdateUserPositionV1Request)updateRequest;

        checkArgument(isNotEmpty(updateV1Request.positionId()), "positionId must be provided.");

        Position position =
            positionRepository.findById(updateV1Request.positionId())
                .orElseThrow(() -> new NotFoundException("Position not found"));

        UserPosition prevPosition =
            userPositionRepository.getUserPosition(uniqueUserId)
                .orElse(null);

        // 기본 prev 가 있는 경우 제거
        if (isNotEmpty(prevPosition)) {
            userPositionRepository.delete(prevPosition);
        }

        UserPosition createdUserPosition =
            UserPosition.builder()
                .userId(uniqueUserId)
                .build();

        position.addUserPosition(createdUserPosition);

        createdUserPosition = userPositionRepository.save(createdUserPosition);

        return from(uniqueUserId, position);
    }

    @Override
    public UserPositionResponse getUserPosition(String uniqueUserId) {

        UserPosition userPosition =
            userPositionRepository.getUserPosition(uniqueUserId)
                .orElseThrow(() -> new NotFoundException(uniqueUserId));

        return from(uniqueUserId, userPosition.getPosition());
    }
}
