package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto;

public class RequestWorldDTO {
    public record WorldIdDDTO(Integer worldId) {}

    public record CreateWorldDTO(
            String WorldName,
            Boolean isPortalEnable,
            Integer towardWorldPartId
    ) {}

}
