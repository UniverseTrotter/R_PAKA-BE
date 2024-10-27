package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto.ScenarioTagDTO;

import java.util.List;

/**
 * @param scenarioCode -1 이면 새로 생성
 * */
public record ScenarioDTO(
        Long scenarioCode,
        String scenarioTitle,
        List<GenreDTO> genre,
        String mainQuest,
        List<String> subQuest,
        String detail,
        List<ScenarioAvatarDTO> scenarioAvatarList,
        List<WorldPartDTO> worldParts,
        List<ScenarioTagDTO> tags
) {
    public static ScenarioDTO getEmptyDTO() {
        return new ScenarioDTO(
                -1L,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static ScenarioDTO insertCreateCode(RequestScenarioDTO.CreateScenarioDTO createDTO) {
        return new ScenarioDTO(
                -1L,
                createDTO.scenarioTitle(),
                createDTO.genre(),
                createDTO.mainQuest(),
                createDTO.subQuest(),
                createDTO.detail(),
                createDTO.scenarioAvatarList(),
                createDTO.worldParts(),
                createDTO.tags()
        );
    }
}
