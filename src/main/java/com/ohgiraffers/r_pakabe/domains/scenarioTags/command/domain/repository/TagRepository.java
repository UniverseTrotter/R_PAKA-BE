package com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.model.ScenarioTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<ScenarioTag, Integer> {
    Optional<ScenarioTag> findByTagName(String name);
}
