package com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.domain.model.UserScenarioSetting;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserScenarioSettingMapper {
    UserScenarioSettingMapper INSTANCE = Mappers.getMapper(UserScenarioSettingMapper.class);

    UserScenarioSettingDTO entityToDto(UserScenarioSetting userScenarioSetting);
    UserScenarioSetting dtoToEntity(UserScenarioSettingDTO userScenarioSettingDTO);
    UserScenarioSetting uploadDtoToEntity(RequestSettingDTO.uploadDTO uploadDTO);

    void updateDtoFromUploadDTO(RequestSettingDTO.uploadDTO uploadDTO, @MappingTarget UserScenarioSettingDTO settingDTO);

}
