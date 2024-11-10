package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunningEntityDTO {
    private String id;
    private Integer roomNum;
    private String scenarioTitle;
    private String mainQuest;
    private List<String> subQuest;
    private String detail;
    private List<PlayerDTO> playerList;
    private List<NpcDTO> npcList;
    private List<String> genre;

}

