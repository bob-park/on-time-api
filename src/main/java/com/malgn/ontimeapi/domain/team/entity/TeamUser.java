package com.malgn.ontimeapi.domain.team.entity;

import static com.google.common.base.Preconditions.*;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

import org.apache.commons.lang.StringUtils;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "teams_users")
public class TeamUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private String userUniqueId;

    @Builder
    private TeamUser(Long id, String userUniqueId) {

        checkArgument(StringUtils.isNotBlank(userUniqueId), "userUniqueId must be provided.");

        this.id = id;
        this.userUniqueId = userUniqueId;
    }

    /*
     * 편의 메서드
     */
    public void updateTeam(Team team) {
        this.team = team;
    }
}
