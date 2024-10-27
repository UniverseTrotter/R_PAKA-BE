package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto;

public class ResponseScenarioDTO {
    public record AllScenarioIDDTO(
            Long scenarioCode,
            String scenarioTitle
    ){}
}
