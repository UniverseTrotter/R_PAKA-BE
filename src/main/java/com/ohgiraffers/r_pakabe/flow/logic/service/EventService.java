package com.ohgiraffers.r_pakabe.flow.logic.service;


import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DialogAiStartDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.service.AiRequestService;
import com.ohgiraffers.r_pakabe.flow.logic.dto.RequestPlayDTO;
import com.ohgiraffers.r_pakabe.flow.logic.dto.ResponsePlayDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.service.RunningStoryAppService;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.ResponseHistoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.service.SceneHistoryAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class EventService {

    private final AiRequestService aiService;
    private final SceneHistoryAppService historyService;
    private final RunningStoryAppService runningService;


    public Mono<ResponsePlayDTO.DialogOpeningDTO> startDialog(RequestPlayDTO.DialogStartDTO dialogStartDTO) {
        ResponseHistoryDTO.HistoryListDTO historyDTO = historyService.getSceneHistory(dialogStartDTO.roomNum());
        List<String> historyList = trimHistoryList(historyDTO);
        DialogAiStartDTO startDTO = new DialogAiStartDTO(
                dialogStartDTO.roomNum(),
                dialogStartDTO.location(),
                dialogStartDTO.npc(),
                historyList
        );
        return aiService.startDialog(startDTO)
                .map(response -> new ResponsePlayDTO.DialogOpeningDTO(response.getOpeningChat()));
    }

    private List<String> trimHistoryList(ResponseHistoryDTO.HistoryListDTO historyDTO) {
        List<String> historyList = new ArrayList<>();
        int length = historyDTO.historyList().size();
        if (length > 3) length = 3;

        for (int i = 0; i < length; i++) {
            historyList.add(historyDTO.historyList().get(i).history());
        }
        return historyList;
    }




}
