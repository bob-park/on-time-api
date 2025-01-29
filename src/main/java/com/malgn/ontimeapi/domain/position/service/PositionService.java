package com.malgn.ontimeapi.domain.position.service;

import java.util.List;

import com.malgn.ontimeapi.domain.position.model.CreatePositionRequest;
import com.malgn.ontimeapi.domain.position.model.PositionResponse;

public interface PositionService {

    PositionResponse createPosition(CreatePositionRequest createRequest);

    List<PositionResponse> getAll();

}
