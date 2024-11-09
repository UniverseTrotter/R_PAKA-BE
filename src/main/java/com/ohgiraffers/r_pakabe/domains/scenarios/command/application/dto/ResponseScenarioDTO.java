package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto;

import java.util.List;

public class ResponseScenarioDTO {

    public record AllScenarioIDDTO(
            Long scenarioCode,
            String scenarioTitle
    ){}


    public record ScenarioListDTO(
            List<AllScenarioIDDTO> scenarios
    ){}
}
