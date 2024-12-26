package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto;

import java.util.List;

public class ResponseAdventureLogDTO {

    public record ListDTO(
            List<briefDTO> roomList
    ){}

    public record briefDTO(
            Integer roomNum,
            String roomTitle,
            String scenarioTitle,
            String createdAt
    ){}
}
