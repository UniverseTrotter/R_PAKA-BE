package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.RequestWorldDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartMapper;
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
    private final WorldPartMapper mapper;


    public WorldPart uploadWorldPart(WorldPartDTO worldPartDTO) {
        WorldPart worldPart = this.worldPartDomainService.getWorldPart(worldPartDTO.getPartId());
        if (worldPart == null) {
            worldPart = worldPartDomainService.createWorldPart(
                    WorldPart.builder()
                            .partName(worldPartDTO.getPartName())
                            .partType(worldPartDTO.getPartType())
                            .isPortalEnable(worldPartDTO.getIsPortalEnable())
                            .towardWorldPartId(worldPartDTO.getTowardWorldPartId())
                            .build()
            );
            log.info("Create worldPart Because not found : {}", worldPart);
        }
        return worldPart;
    }

    @Transactional(readOnly = true)
    public List<WorldPartDTO> findAllWorld() {
        List<WorldPart> worldParts = worldPartDomainService.getAllWorldParts();
        List<WorldPartDTO> worldPartDTOS = new ArrayList<>();
        for (WorldPart worldPart : worldParts) {
            worldPartDTOS.add(mapper.toDTO(worldPart));
        }
        return worldPartDTOS;
    }

    @Transactional(readOnly = true)
    public WorldPartDTO findWorldPartById(Integer worldId) {
        WorldPart worldPart = worldPartDomainService.getWorldPart(worldId);
        if (worldPart == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_WORLD);
        }
        return mapper.toDTO(worldPart);
    }

    @Transactional
    public WorldPartDTO createWorld(RequestWorldDTO.CreateWorldDTO createWorldDTO) {
        WorldPartDTO createWorldPart = mapper.createDtoToDto(createWorldDTO);
        WorldPart entity = worldPartDomainService.createWorldPart(mapper.toEntity(createWorldPart));
        log.info("Created WorldPart : {}", entity);
        return mapper.toDTO(entity);
    }

    @Transactional
    public WorldPartDTO updateWorld(RequestWorldDTO.UpdateWorldDTO updateWorldDTO) {
        WorldPart worldPart = worldPartDomainService.getWorldPart(updateWorldDTO.partId());
        if (worldPart == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_WORLD);
        }
        WorldPartDTO worldPartDTO = mapper.toDTO(worldPart);
        mapper.updateWorldDto(worldPartDTO,updateWorldDTO);

        worldPart = worldPartDomainService.updateWorldPart(mapper.toEntity(worldPartDTO));
        log.info("Updated WorldPart : {}", worldPart);
        return mapper.toDTO(worldPart);
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
