package com.ohgiraffers.r_pakabe.flow.logic.dto;

public enum AnalyzedEvent {
    DIALOG("진행"),
    DICE("다이스"),
    BATTLE("전투"),
    ERROR("ERROR");

    private final String description;

    AnalyzedEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
