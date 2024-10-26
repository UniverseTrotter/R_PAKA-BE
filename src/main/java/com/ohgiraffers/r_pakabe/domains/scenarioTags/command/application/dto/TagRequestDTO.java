package com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto;

public class TagRequestDTO {
    public record TagNameDTO(
            String tagName
    ) {}

    public record TagIdDTO(
            Integer tagId
    ) {}
}
