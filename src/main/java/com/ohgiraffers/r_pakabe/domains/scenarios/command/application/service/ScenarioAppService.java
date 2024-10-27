package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.RequestScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.ScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model.Scenario;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.service.ScenarioDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.service.ScenarioJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ScenarioAppService {

    private final ScenarioDomainService scenarioDomainService;
    private final ScenarioJoinService scenarioJoinService;

    public List<ScenarioDTO> getAllScenarioList() {
        List<Scenario> scenarioList = scenarioDomainService.getAllScenarios();
        List<ScenarioDTO> DTOList = new ArrayList<>();
        for (Scenario scenario : scenarioList) {
            DTOList.add(scenarioJoinService.getDataFromEntity(scenario));
        }
        return DTOList;
    }


    public ScenarioDTO getScenario(Long scenarioCode) {
        Scenario scenario = scenarioDomainService.getScenarioById(scenarioCode);
        if (scenario == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO);
        }
        return scenarioJoinService.getDataFromEntity(scenario);
    }

    public void createScenario(RequestScenarioDTO.CreateScenarioDTO  createDTO) {
        ScenarioDTO scenarioDTO = ScenarioDTO.insertCreateCode(createDTO);
        Scenario scenario = scenarioJoinService.createEntityFromDTO(scenarioDTO);
        scenarioDomainService.createScenario(scenario);
        //코드 지정 있나 체크, 없으면 새로 만들고
    }

    public void updateScenario(ScenarioDTO scenarioDTO) {
        //있으면 기존꺼 가져와야함
        Scenario scenario = scenarioDomainService.getScenarioById(scenarioDTO.scenarioCode());
        if (scenario == null) { //그런거 없다
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO);
        }
        //기존꺼에 새로운 데이터 끼워넣기 ToDo: 끼워넣기 로직
        scenario = scenarioJoinService.createEntityFromDTO(scenarioDTO);

        scenarioDomainService.updateScenario(scenario);
    }
}
