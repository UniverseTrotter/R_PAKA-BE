package com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO;

import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto.SingleLineDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEndDTO {
    private Integer roomNum;
    private List<SingleLineDTO> dialogs;
}
