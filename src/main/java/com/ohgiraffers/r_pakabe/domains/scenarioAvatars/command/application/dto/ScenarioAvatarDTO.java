package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model.ScenarioAvatar;

public record ScenarioAvatarDTO(
        Integer scenarioAvatarId,
        String avatarName,
        Integer outfit,
        Boolean isPlayalbe
) {
    public static ScenarioAvatarDTO fromEntity(final ScenarioAvatar entity) {
        return new ScenarioAvatarDTO(
                entity.getScenarioAvatarId(),
                entity.getAvatarName(),
                entity.getOutfit(),
                entity.getIsPlayable()
        );
    }

    public static ScenarioAvatarDTO getEmpty() {
        return new ScenarioAvatarDTO(
                -1,
                "Empty",
                -1,
                false
        );
    }
}
