package com.malgn.ontimeapi.domain.team.repository.query;

import java.util.List;

import com.malgn.ontimeapi.domain.team.entity.Team;

public interface TeamQueryRepository {

    List<Team> getAll();

}
