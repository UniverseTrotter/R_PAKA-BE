package com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiceDialogDTO {
    private Integer roomNum;
    private String userChat;
    private String diceResult;
}
