package com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DialogStartResponseDTO {
    @JsonProperty("OpeningChat")
    private String OpeningChat;
}
