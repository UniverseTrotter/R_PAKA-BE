package com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndDialogDTO {
    private String history;
    private String isMainQuestClear;
    private List<Integer> clearSubQuestNum;
}
