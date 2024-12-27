package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.model.AdventureLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdventureLogRepository extends JpaRepository<AdventureLog, Long> {
    Optional<AdventureLog> findByRoomNum(Integer roomNum);

    boolean existsByRoomNum(Integer roomNum);
}
