package com.malgn.ontimeapi.domain.team.service.v1;

import static com.google.common.base.Preconditions.*;
import static com.malgn.ontimeapi.domain.team.model.v1.TeamV1Response.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.common.exception.AlreadyExistException;
import com.malgn.common.exception.NotFoundException;
import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.entity.TeamUser;
import com.malgn.ontimeapi.domain.team.model.AddTeamUserRequest;
import com.malgn.ontimeapi.domain.team.model.RemoveTeamUserRequest;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;
import com.malgn.ontimeapi.domain.team.model.v1.AddTeamUserV1Request;
import com.malgn.ontimeapi.domain.team.model.v1.RemoveTeamUserV1Request;
import com.malgn.ontimeapi.domain.team.repository.TeamRepository;
import com.malgn.ontimeapi.domain.team.repository.TeamUserRepository;
import com.malgn.ontimeapi.domain.team.service.TeamUserService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TeamUserV1Service implements TeamUserService {

    private final TeamRepository teamRepository;
    private final TeamUserRepository teamUserRepository;

    @Transactional
    @Override
    public TeamResponse addTeamUsers(Id<Team, Long> teamId, AddTeamUserRequest addRequest) {

        AddTeamUserV1Request addV1Request = (AddTeamUserV1Request)addRequest;

        checkArgument(!addV1Request.userUniqueIds().isEmpty(), "userUniqueIds must be provided.");

        Team team =
            teamRepository.findById(teamId.getValue())
                .orElseThrow(() -> new NotFoundException(teamId));

        for (String userUniqueId : addRequest.userUniqueIds()) {

            TeamUser createdTeamUser =
                TeamUser.builder()
                    .userUniqueId(userUniqueId)
                    .build();

            try {
                team.addUser(createdTeamUser);
            } catch (AlreadyExistException e) {
                log.warn("already exists user id: {}", userUniqueId);
                continue;
            }

            createdTeamUser = teamUserRepository.save(createdTeamUser);

            log.debug("add team user: {}", createdTeamUser);

        }

        return from(team);
    }

    @Transactional
    @Override
    public TeamResponse removeTeamUsers(Id<Team, Long> teamId, RemoveTeamUserRequest removeRequest) {

        RemoveTeamUserV1Request removeV1Request = (RemoveTeamUserV1Request)removeRequest;

        checkArgument(!removeV1Request.userUniqueIds().isEmpty(), "userUniqueIds must be provided.");

        Team team =
            teamRepository.findById(teamId.getValue())
                .orElseThrow(() -> new NotFoundException(teamId));

        for (String userUniqueId : removeV1Request.userUniqueIds()) {

            long count = teamUserRepository.deleteTeamUser(teamId, userUniqueId);

            if (count < 1) {
                log.warn("No exist team user: {}", userUniqueId);
                continue;
            }

            log.debug("remove team user: {}", userUniqueId);

        }

        return from(team);
    }
}
