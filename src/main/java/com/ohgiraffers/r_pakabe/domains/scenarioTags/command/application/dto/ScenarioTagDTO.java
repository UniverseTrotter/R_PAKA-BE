package com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.model.ScenarioTag;

public record ScenarioTagDTO(
        Integer tagCode,
        String tagName
) {
    public static ScenarioTagDTO fromEntity(final ScenarioTag scenarioTag) {
        return new ScenarioTagDTO(scenarioTag.getTagCode(), scenarioTag.getTagName());
    }

    public static ScenarioTagDTO getEmpty(){
        return new ScenarioTagDTO(-1, "Empty");
    }
}
