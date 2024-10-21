package com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model;

import com.ohgiraffers.r_pakabe.common.BaseTimeEntity;
import com.ohgiraffers.r_pakabe.common.JsonArrayConverter;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserAvatarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table
public class Scenario extends BaseTimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String scenarioCode;

    @Column(length = 250)
    private String scenarioTitle;

    @Convert(converter = JsonArrayConverter.class)
    @Column(length = 1000)
    private String genre;

    @Column(length = 1000)
    private String mainQuest;

    @Convert(converter = JsonArrayConverter.class)
    @Column(length = 10000)
    private String subQuest;

    @Column(length = 10000)
    private String detail;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "AVATAR_IDS", referencedColumnName = "AVATAR_ID")
    private List<ScenarioAvatar> scenarioAvatarList;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "WORLD_PART_ID", referencedColumnName = "PART_ID")
    private List<WorldPart> worldParts;

    @Convert(converter = JsonArrayConverter.class)
    @Column
    private String tags;

}
