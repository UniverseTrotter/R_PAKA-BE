package com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
    Optional<Scenario> findByScenarioTitle(String scenarioName);
}
