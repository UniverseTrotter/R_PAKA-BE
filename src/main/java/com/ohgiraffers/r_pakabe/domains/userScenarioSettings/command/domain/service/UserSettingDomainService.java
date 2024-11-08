package com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.domain.model.UserScenarioSetting;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.domain.repository.UserScenarioSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserSettingDomainService {

    private final UserScenarioSettingRepository repository;

    public List<UserScenarioSetting> getAllSettings() {
        return repository.findAll();
    }

    public UserScenarioSetting getUserSettingById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<UserScenarioSetting> getUserSettingByUserCode(Long userCode) {
        return repository.findByUserCode(userCode);
    }

    public UserScenarioSetting getUserSettingByUserCodeAndScenarioCode(Long userCode, Long scenarioCode) {
        return repository.findByUserCodeAndScenarioCode(userCode, scenarioCode).orElse(null);
    }

    public UserScenarioSetting createUserSetting(UserScenarioSetting userSetting) {
        return repository.save(userSetting);
    }

    public UserScenarioSetting updateUserSetting(UserScenarioSetting userSetting) {
        return repository.save(userSetting);
    }

    public void deleteUserSetting(Long id) {
        repository.deleteById(id);
    }

}
