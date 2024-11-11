package com.ohgiraffers.r_pakabe.flow.logic.dto;

public class ResponsePlayDTO {

    public record DialogDTO(
            Integer roomNum,
            String event,
            String bonus,
            String npcChat
    ){}


    public record DiceRollDTO(
            Integer diceFst,
            Integer diceSnd
    ){}

}
