package com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.service.ScenarioAvatarDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.service.WorldPartDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model.Scenario;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.repository.ScenarioRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioDomainService {

    private final ScenarioRepository scenarioRepository;

    public ScenarioDomainService(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }


    public List<Scenario> getAllScenarios() {
        return scenarioRepository.findAll();
    }

    @Nullable
    public Scenario getScenarioById(Long scenarioCode) {
        return scenarioRepository.findById(scenarioCode).orElse(null);
    }

    @Nullable
    public Scenario getScenarioByName(String scenarioName) {
        return scenarioRepository.findByScenarioTitle(scenarioName).orElse(null);
    }

    public Scenario createScenario(Scenario scenario) {
        return scenarioRepository.save(scenario);
    }

    public Scenario updateScenario(Scenario scenario) {
        return scenarioRepository.save(scenario);
    }

    public void deleteScenario(Long scenarioCode) {
        scenarioRepository.deleteById(scenarioCode);
    }



}
