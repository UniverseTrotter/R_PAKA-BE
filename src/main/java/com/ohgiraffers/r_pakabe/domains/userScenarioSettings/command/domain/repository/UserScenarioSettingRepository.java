package com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.domain.model.UserScenarioSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserScenarioSettingRepository extends JpaRepository<UserScenarioSetting, Long> {
    List<UserScenarioSetting> findByUserCode(Long userCode);

    Optional<UserScenarioSetting> findByUserCodeAndScenarioCode(Long userCode, Long scenarioCode);
}
