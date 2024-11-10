package com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 진행중인 방 정보
 * */
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Document
public class RunningStory {

    @Id
    private String id;

    @Indexed(unique = true)
    private Integer roomNum;

    private String scenarioTitle;

    private String mainQuest;

    private List<String> subQuest;

    private String detail;

    private List<String> scenarioAvatarList;

    private List<String> genre;


}
