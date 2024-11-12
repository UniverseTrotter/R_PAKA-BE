package com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model;

import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.NpcDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.PlayerDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
    private List<PlayerDTO> playerList;
    private List<NpcDTO> npcList;
    private List<String> genre;


    @CreatedDate
    private LocalDateTime startAt;
    private LocalDateTime endAt;

}
