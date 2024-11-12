package com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DialogAiStartDTO {
    private Integer roomNum;
    private String location;
    private String npcName;
    private List<String> history;
}
