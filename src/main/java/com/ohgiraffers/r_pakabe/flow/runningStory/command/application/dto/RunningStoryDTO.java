package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto;


import java.util.List;

public record RunningStoryDTO(
         String id,
         Integer roomNum,
         String scenarioTitle,
         String mainQuest,
         List<String> subQuest,
         String detail,
         List<String> scenarioAvatarList,
         List<String> genre
){}

