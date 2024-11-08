package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model.ScenarioAvatar;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScenarioAvatarMapper {
    ScenarioAvatarMapper INSTANCE = Mappers.getMapper(ScenarioAvatarMapper.class);

    ScenarioAvatarDTO toScenarioAvatarDTO(ScenarioAvatar entity);

    ScenarioAvatar toScenarioAvatarEntity(ScenarioAvatarDTO dto);

    // CreateAvatarDTO -> ScenarioAvatarDTO 변환 메서드
    ScenarioAvatarDTO toScenarioAvatarDTO(RequestAvatarDTO.CreateAvatarDTO createAvatarDTO);

    // ScenarioAvatarDTO -> CreateAvatarDTO 변환 메서드
    RequestAvatarDTO.CreateAvatarDTO toCreateAvatarDTO(ScenarioAvatarDTO scenarioAvatarDTO);


    ScenarioAvatar toScenarioAvatarEntity(RequestAvatarDTO.CreateAvatarDTO createAvatarDTO);


}
