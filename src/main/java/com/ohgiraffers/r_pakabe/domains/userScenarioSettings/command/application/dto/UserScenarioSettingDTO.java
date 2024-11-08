package com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Record 로 하려고 했으나, mapstruct 쓰기엔 이게 더 좋음
 * setter 가 없어서 매핑이 매우 복잡해짐
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserScenarioSettingDTO {
    private Long settingId;
    private Long userCode;
    private Long scenarioCode;
    private Integer health;
    private Integer strength;
    private Integer dex;
}