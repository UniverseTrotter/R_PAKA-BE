package com.ohgiraffers.r_pakabe.flow.logic.service;


import com.ohgiraffers.r_pakabe.flow.aiComm.service.AiRequestService;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.service.RunningStoryAppService;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.service.SceneHistoryAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class EventService {

    private final AiRequestService aiService;
    private final SceneHistoryAppService historyService;
    private final RunningStoryAppService runningService;




}
