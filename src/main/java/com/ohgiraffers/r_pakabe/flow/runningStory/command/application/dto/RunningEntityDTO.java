package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity 컨트롤용
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunningEntityDTO {
    private String id;
    private Integer roomNum;
    private String roomTitle;
    private String scenarioTitle;
    private String mainQuest;
    private List<String> subQuest;
    private String detail;
    private List<PlayerDTO> playerList;
    private List<NpcDTO> npcList;
    private List<String> genre;

    private String startAt;
    private String endAt;
}

