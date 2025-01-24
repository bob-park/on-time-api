package com.malgn.ontimeapi.domain.team.entity;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang.StringUtils.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

import org.apache.commons.lang3.StringUtils;

import com.malgn.common.entity.BaseEntity;
import com.malgn.common.exception.AlreadyExistException;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "teams")
public class Team extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Boolean isDeleted;

    @Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<TeamUser> teamUsers = new ArrayList<>();

    @Builder
    private Team(Long id, String name, String description, Boolean isDeleted) {

        checkArgument(isNotBlank(name), "name must be provided.");

        this.id = id;
        this.name = name;
        this.description = description;
        this.isDeleted = defaultIfNull(isDeleted, false);
    }

    /*
     * 편의 메서드
     */
    public void addUser(TeamUser teamUser) {

        if (getTeamUsers().stream()
            .anyMatch(item -> StringUtils.equals(item.getUserUniqueId(), teamUser.getUserUniqueId()))) {
            throw new AlreadyExistException("userId=" + teamUser.getUserUniqueId());
        }

        teamUser.updateTeam(this);

        getTeamUsers().add(teamUser);
    }

}
