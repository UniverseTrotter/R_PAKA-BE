package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.RequestWorldDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model.WorldPart;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.service.WorldPartDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class WorldPartAppService {
    private final WorldPartDomainService worldPartDomainService;

//    public WorldPartDTO loadWorldPart(Integer worldPartId) {
//        WorldPart worldPart = worldPartDomainService.getWorldPart(worldPartId);
//        if (worldPart == null) {
//            return WorldPartDTO.getEmpty();
//        }else {
//            return WorldPartDTO.fromEntity(worldPart);
//        }
//    }

    public WorldPart uploadWorldPart(WorldPartDTO worldPartDTO) {
        log.info("Upload WorldPart : {}", worldPartDTO);
        WorldPart worldPart = this.worldPartDomainService.getWorldPart(worldPartDTO.partId());
        if (worldPart == null) {
            worldPart = worldPartDomainService.createWorldPart(
                    WorldPart.builder()
                            .partName(worldPartDTO.partName())
                            .isPortalEnable(worldPartDTO.isPortalEnable())
                            .build()
            );
        }
        return worldPart;
    }

    @Transactional(readOnly = true)
    public List<WorldPartDTO> findAllWorld() {
        List<WorldPart> worldParts = worldPartDomainService.getAllWorldParts();
        List<WorldPartDTO> worldPartDTOS = new ArrayList<>();
        for (WorldPart worldPart : worldParts) {
            worldPartDTOS.add(WorldPartDTO.fromEntity(worldPart));
        }
        return worldPartDTOS;
    }

    @Transactional(readOnly = true)
    public WorldPartDTO findWorldPartById(Integer worldId) {
        WorldPart worldPart = worldPartDomainService.getWorldPart(worldId);
        if (worldPart == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_WORLD);
        }
        return WorldPartDTO.fromEntity(worldPart);
    }

    @Transactional
    public WorldPartDTO createWorld(RequestWorldDTO.CreateWorldDTO createWorldDTO) {
        WorldPart worldPart = WorldPart.builder()
                .partName(createWorldDTO.WorldName())
                .isPortalEnable(createWorldDTO.isPortalEnable())
                .towardWorldPartId(createWorldDTO.towardWorldPartId())
                .build();
        worldPart = worldPartDomainService.createWorldPart(worldPart);
        log.info("Created WorldPart : {}", worldPart);
        return WorldPartDTO.fromEntity(worldPart);
    }

    @Transactional
    public WorldPartDTO updateWorld(WorldPartDTO worldDTO) {
        WorldPart worldPart = worldPartDomainService.getWorldPart(worldDTO.partId());
        if (worldPart == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_WORLD);
        }

        worldPart = new WorldPart(
                worldPart.getPartId(),
                worldDTO.partName(),
                worldDTO.isPortalEnable(),
                worldDTO.towardWorldPartId()
        );
        worldPart = worldPartDomainService.updateWorldPart(worldPart);
        log.info("Updated WorldPart : {}", worldPart);
        return WorldPartDTO.fromEntity(worldPart);
    }

    @Transactional
    public void deleteWorld(Integer worldId) {
        WorldPart worldPart = worldPartDomainService.getWorldPart(worldId);
        if (worldPart == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_WORLD);
        }
        worldPartDomainService.deleteWorldPart(worldId);
        log.info("Deleted WorldPart : {}", worldPart);
    }
}
