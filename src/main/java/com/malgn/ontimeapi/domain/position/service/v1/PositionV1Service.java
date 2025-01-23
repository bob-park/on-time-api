package com.malgn.ontimeapi.domain.position.service.v1;

import static com.malgn.ontimeapi.domain.position.model.v1.PositionV1Response.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.ontimeapi.domain.position.entity.Position;
import com.malgn.ontimeapi.domain.position.model.CreatePositionRequest;
import com.malgn.ontimeapi.domain.position.model.PositionResponse;
import com.malgn.ontimeapi.domain.position.model.v1.CreatePositionV1Request;
import com.malgn.ontimeapi.domain.position.model.v1.PositionV1Response;
import com.malgn.ontimeapi.domain.position.repository.PositionRepository;
import com.malgn.ontimeapi.domain.position.service.PositionService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PositionV1Service implements PositionService {

    private final PositionRepository positionRepository;

    @Transactional
    @Override
    public PositionResponse createPosition(CreatePositionRequest createRequest) {

        CreatePositionV1Request createV1Request = (CreatePositionV1Request)createRequest;

        Position createdPosition =
            Position.builder()
                .name(createV1Request.name())
                .description(createV1Request.description())
                .build();

        createdPosition = positionRepository.save(createdPosition);

        log.debug("created position. ({})", createdPosition);

        return from(createdPosition);
    }

    @Override
    public List<PositionResponse> getAll() {

        List<Position> result = positionRepository.getAll();

        return result.stream()
            .map(PositionV1Response::from)
            .toList();
    }
}
