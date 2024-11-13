package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunningStoryDTO{

    private Integer roomNum;
    private String scenarioTitle;
    private String mainQuest;
    private List<String> subQuest;
    private String detail;
    private List<String> worldParts;
    private List<PlayerDTO> playerList;
    private List<NpcDTO> npcList;
    private List<String> genre;

    private String startAt;
    private String endAt;
}

