package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarMapper;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.model.SceneHistory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoryMapper {

    ScenarioAvatarMapper INSTANCE = Mappers.getMapper(ScenarioAvatarMapper.class);
    SceneHistoryDTO toDto(SceneHistory entity);
    SceneHistory toEntity(SceneHistoryDTO dto);

    HistoryDto entityToHistoryDto(SceneHistory entity);

    SceneHistory createdToToEntity(RequestHistoryDTO.createDTO createDTO);
}
