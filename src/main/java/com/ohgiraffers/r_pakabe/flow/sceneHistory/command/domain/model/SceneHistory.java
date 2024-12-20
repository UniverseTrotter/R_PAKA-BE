package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.model;

import com.ohgiraffers.r_pakabe.common.PolyTime;
import com.ohgiraffers.r_pakabe.common.PolyTimeAttributeConverter;
import jakarta.persistence.Convert;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 진행상황 기록
 * */
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Document
public class SceneHistory {

    @Id
    private String id;

    private Integer roomNum;

    private String history;

    @CreatedDate
    private LocalDateTime createdAt;
}

