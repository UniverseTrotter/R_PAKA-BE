package com.ohgiraffers.r_pakabe.flow.logic.dto;

import java.util.List;

public class RequestPlayDTO {

    public record roomStartDTO(
            Integer roomNum,
            Integer scenarioId,
            List<Integer> userCodes
    ){}

    public record DialogStartDTO(
            Integer roomNum,
            Integer location,
            Integer npc
    ){}

    public record DiceResultDTO(
            Integer roomNum,
            Integer diceFst,
            Integer diceSnd
    ){}

    public record BattleResultDTO(
            Integer roomNum,
            Boolean isBattleWon,
            List<UserStatusDTO> userSatusList
    ){}

    public record UserStatusDTO(
            Long  userCode,
            Integer healthPoint,
            List<String> status
    ){}
}
