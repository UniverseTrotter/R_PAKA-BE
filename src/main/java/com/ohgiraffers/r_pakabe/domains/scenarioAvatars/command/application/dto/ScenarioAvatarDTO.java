package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto;


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
        Float rotation,
        Integer worldId
) {}
