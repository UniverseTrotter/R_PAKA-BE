package com.ohgiraffers.r_pakabe.flow.aiComm.dto;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarMapper;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.RoomStartDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.NpcDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.PlayerDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AiDtoMapper {


    @Mapping(target = "playerList", source = "playerList")
    @Mapping(target = "npcList", source = "npcList")
    RoomStartDTO runningToStart(RunningStoryDTO runningDTO);

    default List<String> mapPlayerList(List<PlayerDTO> players) {
        if (players == null) {
            return null;
        }
        return players.stream()
                .map(PlayerDTO::getNickname) // 닉네임만 추출
                .collect(Collectors.toList());
    }

    default List<String> mapnpcList(List<NpcDTO> npcs) {
        if (npcs == null) {
            return null;
        }
        return npcs.stream()
                .map(NpcDTO::getAvatarName) // 이름만 추출
                .collect(Collectors.toList());
    }
}
