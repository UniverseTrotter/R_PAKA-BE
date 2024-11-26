package com.ohgiraffers.r_pakabe.flow.logic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponsePlayDTO {

    public record DialogOpeningDTO(
            String OpeningChat
    ){}

    public record RoomOpeningDTO(
            @JsonProperty("gm_msg")
            String OpeningChat
    ){}

    public record EventDTO(
            String event,
            String bonus,
            String npcChat
    ){}


    public record DiceRollDTO(
            Integer diceFst,
            Integer diceSnd
    ){}

    public record EndResultDTO(
            String history,
            String isMainQuestClear,
            List<Integer>clearSubQuestNum
    ){}


    public record RunningListDTO(
            Integer roomNum,
            String scenarioTitle
    ){}


}
