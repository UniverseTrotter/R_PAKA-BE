package com.ohgiraffers.r_pakabe.flow.dialogArchive.command.domain.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Document
public class DialogArchive {

    @Id
    private String id;

    private Integer roomNum;

    private String speaker;

    private String dialog;

    @CreatedDate
    private LocalDateTime createdAt;

}
