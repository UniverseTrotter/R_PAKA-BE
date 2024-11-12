package com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
/**
 * 엔티티 컨트롤 용
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DialogArchiveDTO {

    private String id;

    private Integer roomNum;

    private String speaker;

    private String dialog;

    private LocalDateTime createdAt;
}
