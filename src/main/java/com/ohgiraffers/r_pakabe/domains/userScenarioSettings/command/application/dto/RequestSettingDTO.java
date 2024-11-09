package com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto;

public class RequestSettingDTO {

    public record findOneDTO(
            Long userCode,
            Long scenarioCode
    ){}

    public record uploadDTO(
            Long userCode,
            Long scenarioCode,
            Integer health,
            Integer strength,
            Integer dex
    ) {}

}
