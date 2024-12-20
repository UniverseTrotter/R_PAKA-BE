package com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomAiStartDTO {
    private Integer roomNum;
    private String title;
    private List<String> genre;
    private String mainQuest;
    private List<String> subQuest;
    private String detail;
    private List<String> worldParts;
    private List<String> playerList;
    private List<npcInfoDto> npcList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class npcInfoDto {
        private String avatarName;
        private String avatarDetail;
    }
}
