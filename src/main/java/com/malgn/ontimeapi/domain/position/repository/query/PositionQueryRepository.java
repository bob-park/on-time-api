package com.malgn.ontimeapi.domain.position.repository.query;

import java.util.List;

import com.malgn.ontimeapi.domain.position.entity.Position;

public interface PositionQueryRepository {

    List<Position> getAll();

}
