package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto;

import java.time.LocalDateTime;

public record HistoryDto(
        String history,
        String createdAt
){}
