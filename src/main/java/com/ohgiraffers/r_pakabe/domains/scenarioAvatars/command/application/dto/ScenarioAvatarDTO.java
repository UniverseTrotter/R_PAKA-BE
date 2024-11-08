package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model.ScenarioAvatar;

public record ScenarioAvatarDTO(
        Integer scenarioAvatarId,
        String avatarName,
        Integer outfit,
        Boolean isPlayable,
        Integer health,
        Integer strength,
        Integer dex,
        Float axisX,
        Float axisY,
        Float axisZ,
        Integer worldId
) {}
