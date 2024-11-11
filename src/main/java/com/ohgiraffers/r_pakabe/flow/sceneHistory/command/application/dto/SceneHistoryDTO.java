package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity 컨트롤용
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SceneHistoryDTO {

    private String id;
    private Integer roomNum;
    private String history;
    private LocalDateTime createdAt;
}
