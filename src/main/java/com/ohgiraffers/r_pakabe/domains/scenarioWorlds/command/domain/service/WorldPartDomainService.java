package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model.WorldPart;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.repository.WorldPartRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorldPartDomainService {

    private final WorldPartRepository worldPartRepository;

    public WorldPartDomainService(WorldPartRepository worldPartRepository) {
        this.worldPartRepository = worldPartRepository;
    }

    public List<WorldPart> getAllWorldParts() {
        return worldPartRepository.findAll();
    }

    @Nullable
    public WorldPart getWorldPart(Integer worldPartId) {
        return worldPartRepository.findById(worldPartId).orElse(null);
    }

    public WorldPart createWorldPart(WorldPart worldPart) {
        return worldPartRepository.save(worldPart);
    }

    public WorldPart updateWorldPart(WorldPart worldPart) {
        return worldPartRepository.save(worldPart);
    }

    public void deleteWorldPart(Integer worldPartId) {
        worldPartRepository.deleteById(worldPartId);
    }
}
