package com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DialogAnalyzedDTO {
    private String event;
    private String bonus;
    private String gmMsg;
}
