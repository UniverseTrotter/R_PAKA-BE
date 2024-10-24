package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.service;

import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model.WorldPart;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.service.WorldPartDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorldPartAppService {
    private final WorldPartDomainService worldPartDomainService;

    public WorldPartDTO loadWorldPart(Integer worldPartId) {
        WorldPart worldPart = worldPartDomainService.getWorldPart(worldPartId);
        if (worldPart == null) {
            return WorldPartDTO.getEmpty();
        }else {
            return WorldPartDTO.fromEntity(worldPart);
        }
    }

    public WorldPart uploadWorldPart(WorldPartDTO worldPartDTO) {
        WorldPart worldPart = this.worldPartDomainService.getWorldPart(worldPartDTO.partId());
        if (worldPart == null) {
            worldPart = worldPartDomainService.createWorldPart(
                    WorldPart.builder()
                            .partName(worldPartDTO.partName())
                            .isPortalEnable(worldPartDTO.isPortalEnable())
                            .build()
            );
        }else {
            worldPart = this.worldPartDomainService.updateWorldPart(
                    new WorldPart(
                            worldPart.getPartId(),
                            worldPartDTO.partName(),
                            worldPartDTO.isPortalEnable()
                    )
            );
        }
        return worldPart;
    }

}
