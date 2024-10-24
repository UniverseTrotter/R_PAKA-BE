package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model.WorldPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorldPartRepository extends JpaRepository<WorldPart, Integer> {
}
