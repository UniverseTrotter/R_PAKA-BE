package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model.ScenarioAvatar;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.repository.ScenarioAvatarRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioAvatarDomainService {

    private final ScenarioAvatarRepository scenarioAvatarRepository;

    public ScenarioAvatarDomainService(ScenarioAvatarRepository scenarioAvatarRepository) {
        this.scenarioAvatarRepository = scenarioAvatarRepository;
    }

    public List<ScenarioAvatar> getAllScenarioAvatars() {
        return scenarioAvatarRepository.findAll();
    }

    @Nullable
    public ScenarioAvatar getScenarioAvatar(Integer avatarId) {
        return scenarioAvatarRepository.findById(avatarId).orElse(null);
    }

    public ScenarioAvatar createScenarioAvatar(ScenarioAvatar scenarioAvatar) {
        return scenarioAvatarRepository.save(scenarioAvatar);
    }

    public ScenarioAvatar updateScenarioAvatar(ScenarioAvatar scenarioAvatar) {
        return scenarioAvatarRepository.save(scenarioAvatar);
    }

    public void deleteScenarioAvatar(Integer avatarId) {
        scenarioAvatarRepository.deleteById(avatarId);
    }


}
