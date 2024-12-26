package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto;


import java.util.List;

public class ResponseAdventureDTO {

    public record LogList(
            List<ShortenLog> adventures
    ){}

    public record ShortenLog(
            String roomTitle,
            String scenarioTitle,
            Long id,
            String createdAt
    ){}

}
