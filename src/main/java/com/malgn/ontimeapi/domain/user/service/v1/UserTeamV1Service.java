package com.malgn.ontimeapi.domain.user.service.v1;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.common.exception.NotFoundException;
import com.malgn.common.model.Id;
import com.malgn.ontimeapi.domain.team.entity.Team;
import com.malgn.ontimeapi.domain.team.entity.TeamUser;
import com.malgn.ontimeapi.domain.team.model.TeamResponse;
import com.malgn.ontimeapi.domain.team.model.v1.TeamUserV1Response;
import com.malgn.ontimeapi.domain.team.model.v1.TeamV1Response;
import com.malgn.ontimeapi.domain.team.repository.TeamUserRepository;
import com.malgn.ontimeapi.domain.user.model.UserResponse;
import com.malgn.ontimeapi.domain.user.service.UserTeamService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserTeamV1Service implements UserTeamService {

    private final TeamUserRepository teamUserRepository;

    @Override
    public TeamResponse getTeam(Id<UserResponse, String> unqiueId) {

        TeamUser teamUser =
            teamUserRepository.getTeamByUser(unqiueId.getValue())
                .orElseThrow(() -> new NotFoundException(unqiueId));

        Team team = teamUser.getTeam();

        return TeamV1Response.builder()
            .id(team.getId())
            .name(team.getName())
            .description(team.getDescription())
            .teamUsers(List.of(TeamUserV1Response.from(teamUser)))
            .createdDate(team.getCreatedDate())
            .createdBy(team.getCreatedBy())
            .lastModifiedDate(team.getLastModifiedDate())
            .lastModifiedBy(team.getLastModifiedBy())
            .build();
    }
}
