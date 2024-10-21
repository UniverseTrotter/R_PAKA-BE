package com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model;

import jakarta.persistence.*;

@Entity
@Table
public class ScenarioAvatar {
    @Id
    @Column(name = "AVATAR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int senarioAvatarId;

    @Column
    private String avatarName;

    @Column
    private int outfit;
}
