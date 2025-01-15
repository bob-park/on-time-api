package com.malgn.ontimeapi.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.malgn.common.entity.BaseTimeEntity;
import com.malgn.common.entity.annotation.BaseIdGenerateValue;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity<String> {

    @Id
    @BaseIdGenerateValue
    private String id;

    private String name;

    @Builder
    private User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
