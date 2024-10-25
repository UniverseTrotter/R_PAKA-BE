package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto.ScenarioTagDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;

import java.util.List;

public record ScenarioCreateDTO(
        String scenarioTitle,
        List<GenreDTO> genre,
        String mainQuest,
        List<String> subQuest,
        String detail,
        List<ScenarioAvatarDTO> scenarioAvatarList,
        List<WorldPartDTO> worldParts,
        List<ScenarioTagDTO> tags
) {
}
