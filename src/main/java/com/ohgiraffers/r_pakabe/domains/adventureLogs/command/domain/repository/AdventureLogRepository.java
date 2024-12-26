package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.model.AdventureLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureLogRepository extends JpaRepository<AdventureLog, Long> {
}
