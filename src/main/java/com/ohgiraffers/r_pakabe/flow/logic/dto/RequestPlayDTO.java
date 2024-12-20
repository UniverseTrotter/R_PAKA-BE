package com.ohgiraffers.r_pakabe.flow.logic.dto;

import java.util.List;

public class RequestPlayDTO {

    public record roomStartDTO(
            Integer roomNum,
            String roomTitle,
            Long scenarioId,
            List<Long> userCodes
    ){}

    public record DialogStartDTO(
            Integer roomNum,
            String location,
            String npc
    ){}

    public record DialogSendDTO(
            Integer roomNum,
            String userChat
    ){}

    public record DiceResultDTO(
            Integer roomNum,
            Integer diceFst,
            Integer diceSnd,
            String bonus,
            Long userCode
    ){}
    public record BattleResultDTO(
            Integer roomNum,
            Boolean isBattleWon,
            List<UserStatusDTO> userSatusList,
            NpcStatusDTO npcStatusDTO
    ){}

    public record UserStatusDTO(
            Long  userCode,
            Integer healthPoint,
            List<String> status
    ){}

    public record NpcStatusDTO(
            Integer scenarioAvatarId,
            Integer healthPoint,
            List<String> status
    ){}

    public record RoomNumDTO(
            Integer roomNum
    ){}

}
