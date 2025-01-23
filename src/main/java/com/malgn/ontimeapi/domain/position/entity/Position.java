package com.malgn.ontimeapi.domain.position.entity;

import static com.google.common.base.Preconditions.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import com.malgn.ontimeapi.domain.user.entity.UserPosition;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "positions")
public class Position extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private List<UserPosition> userPositions = new ArrayList<>();

    @Builder
    private Position(Long id, String name, String description) {

        checkArgument(StringUtils.isNotBlank(name), "name must be provided.");

        this.id = id;
        this.name = name;
        this.description = description;
    }

    /*
     * 편의 메서드
     */
    public void addUserPosition(UserPosition userPosition) {

        userPosition.updatePosition(this);

        getUserPositions().add(userPosition);
    }

    public void removeUserPosition(UserPosition userPosition) {
        getUserPositions().remove(userPosition);
    }
}
