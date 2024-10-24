package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.ScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model.Scenario;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.service.ScenarioDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.service.ScenarioJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ScenarioAppService {

    private final ScenarioDomainService scenarioDomainService;
    private final ScenarioJoinService scenarioJoinService;


    public ScenarioDTO getScenario(Long scenarioCode) {
        Scenario scenario = scenarioDomainService.getScenarioById(scenarioCode);
        if (scenario == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO);
        }
        return scenarioJoinService.getDataFromEntity(scenario);
    }

    public void createScenario(ScenarioDTO scenarioDTO) {
    }

}
