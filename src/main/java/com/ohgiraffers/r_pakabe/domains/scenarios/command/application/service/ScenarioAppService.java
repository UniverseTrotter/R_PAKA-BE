package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.RequestScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.ResponseScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.ScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model.Scenario;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.service.ScenarioDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.service.ScenarioJoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScenarioAppService {

    private final ScenarioDomainService scenarioDomainService;
    private final ScenarioJoinService scenarioJoinService;

    @Transactional(readOnly = true)
    public List<ResponseScenarioDTO.AllScenarioIDDTO> getAllScenariosId() {
        List<Scenario> scenarios = scenarioDomainService.getAllScenarios();
        List<ResponseScenarioDTO.AllScenarioIDDTO> allScenarioIDDTOS = new ArrayList<>();
        for (Scenario scenario : scenarios) {
            allScenarioIDDTOS.add(new ResponseScenarioDTO.AllScenarioIDDTO(
                    scenario.getScenarioCode(),
                    scenario.getScenarioTitle()
            ));
        }
        return allScenarioIDDTOS;
    }


    @Transactional(readOnly = true)
    public List<ScenarioDTO> getAllScenarioList() {
        List<Scenario> scenarioList = scenarioDomainService.getAllScenarios();
        List<ScenarioDTO> DTOList = new ArrayList<>();
        for (Scenario scenario : scenarioList) {
            DTOList.add(scenarioJoinService.getDataFromEntity(scenario));
        }
        return DTOList;
    }


    @Transactional(readOnly = true)
    public ScenarioDTO getScenario(Long scenarioCode) {
        Scenario scenario = scenarioDomainService.getScenarioById(scenarioCode);
        if (scenario == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO);
        }
        return scenarioJoinService.getDataFromEntity(scenario);
    }

    @Transactional
    public ScenarioDTO createScenario(RequestScenarioDTO.CreateScenarioDTO scenarioDTO) {
        Scenario scenario = scenarioDomainService.getScenarioByName(scenarioDTO.scenarioTitle());
        if (scenario != null) {
            throw new ApplicationException(ErrorCode.SCENARIO_ALREADY_EXIT);
        }
        scenario = scenarioJoinService.createEntityFromDto(
                RequestScenarioDTO.NewScenarioDTO.insertCreateCode(scenarioDTO)
        );
        scenario = scenarioDomainService.createScenario(scenario);
        log.info("Scenario created: {}", scenario);
        return scenarioJoinService.getDataFromEntity(scenario);
    }

    @Transactional
    public ScenarioDTO updateScenario(RequestScenarioDTO.NewScenarioDTO scenarioDTO) {
        Scenario scenario = scenarioDomainService.getScenarioById(scenarioDTO.scenarioCode());
        if (scenario == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO);
        }
        scenario = scenarioJoinService.createEntityFromDto(scenarioDTO);
        scenario = scenarioDomainService.updateScenario(scenario);
        log.info("Scenario updated: {}", scenario);
        return scenarioJoinService.getDataFromEntity(scenario);
    }

    @Transactional
    public void deleteScenario(Long scenarioCode) {
        Scenario scenario = scenarioDomainService.getScenarioById(scenarioCode);
        if (scenario == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO);
        }
        scenarioDomainService.deleteScenario(scenarioCode);
        log.info("Scenario deleted: {}", scenario);
    }















    @Transactional
    public ScenarioDTO createScenario(RequestScenarioDTO.UploadScenarioDTO createDTO) {
        ScenarioDTO scenarioDTO = ScenarioDTO.insertCreateCode(createDTO);
        Scenario scenario = scenarioJoinService.uploadEntityFromDTO(scenarioDTO);
        scenario = scenarioDomainService.createScenario(scenario);
        log.info("Created scenario: {}", scenario);
        return scenarioJoinService.getDataFromEntity(scenario);
    }

    @Transactional
    public void renewScenario(ScenarioDTO scenarioDTO) {
        //있으면 기존꺼 가져와야함
        Scenario scenario = scenarioDomainService.getScenarioById(scenarioDTO.scenarioCode());
        if (scenario == null) { //그런거 없다
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO);
        }
        //기존꺼에 새로운 데이터 끼워넣기 ToDo: 끼워넣기 로직
        scenario = scenarioJoinService.uploadEntityFromDTO(scenarioDTO);

        scenarioDomainService.updateScenario(scenario);
    }
}
