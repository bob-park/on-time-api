package com.malgn.ontimeapi.domain.user.entity;

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

import org.apache.commons.lang3.StringUtils;

import com.malgn.ontimeapi.domain.position.entity.Position;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users_positions")
public class UserPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userUniqueId;

    @Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @Builder
    private UserPosition(Long id, String userUniqueId) {

        checkArgument(StringUtils.isNotEmpty(userUniqueId), "userUniqueId must be provided.");

        this.id = id;
        this.userUniqueId = userUniqueId;
    }

    public void updatePosition(Position position) {
        this.position = position;
    }
}
