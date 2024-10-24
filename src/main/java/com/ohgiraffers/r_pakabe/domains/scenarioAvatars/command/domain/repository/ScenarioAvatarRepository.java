package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model.ScenarioAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioAvatarRepository extends JpaRepository<ScenarioAvatar, Integer> {
}
