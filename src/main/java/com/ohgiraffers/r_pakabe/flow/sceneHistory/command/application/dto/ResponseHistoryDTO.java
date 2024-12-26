package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto;

import java.util.List;

public class ResponseHistoryDTO {

    public record HistoryListDTO(

            Integer roomNum,
            List<HistoryDto> historyList
    ) {
    }

    public record RoomListDTO(
            List<Integer> roomNumList
    ){}
}
