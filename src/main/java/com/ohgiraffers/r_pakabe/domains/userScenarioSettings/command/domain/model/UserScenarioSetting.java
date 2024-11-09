package com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.domain.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * 유저 세팅을 시나리오마다 저장
 * */
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Entity
@Table
public class UserScenarioSetting {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;

    @Column
    private Long userCode;

    @Column
    private Long scenarioCode;


    @Column
    private Integer health;

    @Column
    private Integer strength;

    @Column
    private Integer dex;

}
