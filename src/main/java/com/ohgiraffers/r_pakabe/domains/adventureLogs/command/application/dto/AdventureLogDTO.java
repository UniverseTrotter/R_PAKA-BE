package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdventureLogDTO {

    private Long id;

    private Integer roomNum;
    private String roomTitle;

    //플레이 기록
    private List<String> history;

    // 사용된 시나리오 정보
    private String scenarioTitle;
    private String mainQuest;
    private List<String> subQuest;
    private String detail;
    private List<String> playerList;
    private List<String> npcList;
    private List<String> genre;

    private String startAt;
    private String endAt;

    private String createdAt;
}
