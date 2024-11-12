package com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomArchiveDTO {

    private Integer roomNum;

    private List<String> dialogs;
}
