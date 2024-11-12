package com.ohgiraffers.r_pakabe.flow.logic.service;


import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DialogAiStartDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DiceDialogDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.RequestAnalyzeDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogAnalyzedDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogResponseDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.service.AiRequestService;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto.CreateDialogArchiveDTO;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.service.DialogArchiveAppService;
import com.ohgiraffers.r_pakabe.flow.logic.dto.AnalyzedEvent;
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
    private final DialogArchiveAppService dialogArchiveService;


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
                .map(response -> {
                    dialogArchiveService.save(
                            new CreateDialogArchiveDTO(dialogStartDTO.roomNum(),"player", response.getOpeningChat())
                    );
                    return new ResponsePlayDTO.DialogOpeningDTO(response.getOpeningChat());
                });
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


    public ResponsePlayDTO.EventDTO sendDialog(RequestPlayDTO.DialogSendDTO dialogSendDTO) {
        //ai 로부터 받아옴
        DialogAnalyzedDTO analyzed = aiService.analyzeDialog(dialogSendDTO).block();

        if (analyzed == null || analyzed.getEvent() == null){
            log.error("돌아온 이벤트가 없음");
            throw new ApplicationException(ErrorCode.CANNOT_HANDLE_EVENT);
        }

        ResponsePlayDTO.EventDTO eventDTO;

        switch (getAnalyzedEvent(analyzed.getEvent())) {
            case DIALOG -> eventDTO = responseDialog(
                    new RequestAnalyzeDTO(dialogSendDTO.roomNum(), dialogSendDTO.userChat())
            );
            case DICE -> eventDTO = new ResponsePlayDTO.EventDTO(
                    dialogSendDTO.roomNum(),
                    analyzed.getEvent(),
                    analyzed.getBonus(),
                    ""
            );
            case BATTLE -> eventDTO = new ResponsePlayDTO.EventDTO(
                    dialogSendDTO.roomNum(),
                    analyzed.getEvent(),
                    "",
                    ""
            );
            default -> {
                log.error("이벤트가 Enum 에 있는 값이 아님");
                throw new ApplicationException(ErrorCode.CANNOT_HANDLE_EVENT);
            }
        }


        if (eventDTO == null) {
            log.error("이벤트로 만들어낸 데이터가 문제있음");
            throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        dialogArchiveService.save(
                new CreateDialogArchiveDTO(dialogSendDTO.roomNum(),"player", dialogSendDTO.userChat())
        );

        return eventDTO;
    }

    private static AnalyzedEvent getAnalyzedEvent(String event) {
        try {
            return AnalyzedEvent.valueOf(event);
        } catch (IllegalArgumentException e) {
            return AnalyzedEvent.ERROR;
        }
    }

    public ResponsePlayDTO.EventDTO responseDialog(RequestAnalyzeDTO requestAnalyzeDTO){
        DialogResponseDTO responseDTO = aiService.requestDialog(requestAnalyzeDTO).block();
        if (responseDTO == null || responseDTO.getResponse() == null) {
            log.error("받은 대사가 없음");
            throw new ApplicationException(ErrorCode.CANNOT_HANDLE_EVENT);
        }


        dialogArchiveService.save(
                new CreateDialogArchiveDTO(requestAnalyzeDTO.getRoomNum(),"player", requestAnalyzeDTO.getUserChat())
        );

        return new ResponsePlayDTO.EventDTO(
                requestAnalyzeDTO.getRoomNum(),
                AnalyzedEvent.DIALOG.getDescription(),
                "",
                responseDTO.getResponse()
        );
    }

    public ResponsePlayDTO.EventDTO diceRoll(RequestPlayDTO.DiceResultDTO resultDTO) {
        int diceNum = resultDTO.diceFst() + resultDTO.diceSnd();
        boolean isSuccess;
        if (2 <= diceNum && diceNum <= 6){
            isSuccess = false;
        } else if (7 <= diceNum && diceNum <= 12) {
            isSuccess = true;
        }else {
            throw new ApplicationException(ErrorCode.INVALID_DICE);
        }

        String diceResult = "";
        if (isSuccess) {
            diceResult = "성공";
        }else {
            diceResult = "실패";
        }

        String userChat = dialogArchiveService.findLatestByRoomNum(resultDTO.roomNum()).getDialog();

        DiceDialogDTO requestDto = new DiceDialogDTO(
                resultDTO.roomNum(),
                userChat,
                diceResult
        );

        DialogResponseDTO responseDTO = aiService.responseDice(requestDto).block();
        if (responseDTO == null || responseDTO.getResponse() == null) {
            throw new ApplicationException(ErrorCode.EMPTY_RESPONSE);
        }

        return new ResponsePlayDTO.EventDTO(
                resultDTO.roomNum(),
                AnalyzedEvent.DIALOG.getDescription(),
                "",
                responseDTO.getResponse()
        );
    }
}
