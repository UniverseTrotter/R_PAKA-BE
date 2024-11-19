package com.ohgiraffers.r_pakabe.flow.logic.dto;

import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.UserScenarioSettingDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.NpcDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.PlayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayMapper {
    PlayMapper INSTANCE = Mappers.getMapper(PlayMapper.class);



    void updatePlayerFromDetail(@MappingTarget PlayerDTO playerDTO, UserResponseDTO.UserDetailDTO detailDTO);

    void updatePlayerFromAvatar(@MappingTarget PlayerDTO playerDTO, UserAvatarDTO avatarDTO);

    void updatePlayerFromSetting(@MappingTarget PlayerDTO playerDTO, UserScenarioSettingDTO settingDTO);

    void updateNpcFromAvatarDTO(@MappingTarget NpcDTO npcDTO, ScenarioAvatarDTO avatarDTO);
}
