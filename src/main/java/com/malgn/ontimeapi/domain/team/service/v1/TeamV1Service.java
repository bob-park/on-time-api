package com.malgn.ontimeapi.domain.team.service.v1;

import static com.malgn.ontimeapi.domain.team.model.v1.TeamV1Response.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.common.exception.NotFoundException;
import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.model.CreateTeamRequest;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;
import com.malgn.ontimeapi.domain.team.model.v1.CreateTeamV1Request;
import com.malgn.ontimeapi.domain.team.model.v1.TeamV1Response;
import com.malgn.ontimeapi.domain.team.repository.TeamRepository;
import com.malgn.ontimeapi.domain.team.service.TeamService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TeamV1Service implements TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    @Override
    public TeamResponse createTeam(CreateTeamRequest createRequest) {

        CreateTeamV1Request createV1Request = (CreateTeamV1Request)createRequest;

        Team createdTeam =
            Team.builder()
                .name(createV1Request.name())
                .description(createV1Request.description())
                .build();

        createdTeam = teamRepository.save(createdTeam);

        log.debug("created team: {}", createdTeam);

        return from(createdTeam);
    }

    @Override
    public List<TeamResponse> getAll() {

        List<Team> result = teamRepository.getAll();

        return result.stream()
            .map(TeamV1Response::from)
            .toList();
    }

    @Override
    public TeamResponse getById(Id<Team, Long> teamId) {

        Team team =
            teamRepository.findById(teamId.getValue())
                .orElseThrow(() -> new NotFoundException(teamId));

        return from(team, true);
    }
}
