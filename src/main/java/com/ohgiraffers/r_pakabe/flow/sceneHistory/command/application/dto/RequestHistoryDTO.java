package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto;

public class RequestHistoryDTO {
    public record createDTO(
            Integer roomNum,
            String history
    ){}
}
