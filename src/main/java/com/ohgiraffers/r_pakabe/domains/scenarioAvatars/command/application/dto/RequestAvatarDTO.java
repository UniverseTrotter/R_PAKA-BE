package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto;


public class RequestAvatarDTO {

    public record AvatarIdDTO(
            Integer avatarId
    ) {}

    public record CreateAvatarDTO(
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
    ){}
}
