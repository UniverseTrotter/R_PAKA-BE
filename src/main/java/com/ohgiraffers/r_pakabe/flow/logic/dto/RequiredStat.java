package com.ohgiraffers.r_pakabe.flow.logic.dto;

public enum RequiredStat {
    HEALTH("HEALTH"),
    STRENGTH("STRENGTH"),
    DEX("DEX");

    private final String value;

    RequiredStat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
