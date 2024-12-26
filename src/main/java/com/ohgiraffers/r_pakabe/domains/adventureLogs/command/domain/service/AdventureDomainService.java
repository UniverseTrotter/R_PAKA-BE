package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.model.AdventureLog;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.repository.AdventureLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdventureDomainService {

    private final AdventureLogRepository repository;

    public List<AdventureLog> findAll(){
        return repository.findAll();
    }

    public AdventureLog findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public AdventureLog save(AdventureLog entity) {
        return repository.save(entity);
    }
}
