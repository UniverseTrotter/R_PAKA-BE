package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto;

public class RequestWorldDTO {
    public record WorldIdDDTO(Integer worldId) {}

    public record CreateWorldDTO(
            String WorldName,
            Integer partType,
            Boolean isPortalEnable,
            Integer towardWorldPartId
    ) {}

    public record UpdateWorldDTO(
            Integer partId,
            String WorldName,
            Integer partType,
            Boolean isPortalEnable,
            Integer towardWorldPartId
    ) {}

}
