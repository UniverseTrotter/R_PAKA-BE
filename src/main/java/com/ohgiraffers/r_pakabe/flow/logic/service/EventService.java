package com.ohgiraffers.r_pakabe.flow.logic.service;


import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DialogAiStartDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DiceDialogDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.RequestAnalyzeDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogAnalyzedDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogResponseDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.EndDialogDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.service.AiRequestService;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto.CreateDialogArchiveDTO;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto.RoomArchiveDTO;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.service.DialogArchiveAppService;
import com.ohgiraffers.r_pakabe.flow.logic.dto.AnalyzedEvent;
import com.ohgiraffers.r_pakabe.flow.logic.dto.RequestPlayDTO;
import com.ohgiraffers.r_pakabe.flow.logic.dto.ResponsePlayDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.NpcDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.PlayerDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.service.RunningStoryAppService;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.RequestHistoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.ResponseHistoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.service.SceneHistoryAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                    analyzed.getEvent(),
                    analyzed.getBonus(),
                    ""
            );
            case BATTLE -> eventDTO = new ResponsePlayDTO.EventDTO(
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
        for (AnalyzedEvent e : AnalyzedEvent.values()) { // 모든 열거형 상수 순회
            if (e.getDescription().equals(event)) {      // description과 입력값 비교
                return e;                                // 일치하는 상수 반환
            }
        }
        return AnalyzedEvent.ERROR;                     // 없으면 ERROR 반환
    }


    public ResponsePlayDTO.EventDTO responseDialog(RequestAnalyzeDTO requestAnalyzeDTO){
        log.info("request Analyze : {}", requestAnalyzeDTO.getUserChat());
        DialogResponseDTO responseDTO = aiService.requestDialog(requestAnalyzeDTO).block();
        if (responseDTO == null || responseDTO.getResponse() == null) {
            log.error("받은 대사가 없음");
            throw new ApplicationException(ErrorCode.CANNOT_HANDLE_EVENT);
        }


        dialogArchiveService.save(
                new CreateDialogArchiveDTO(requestAnalyzeDTO.getRoomNum(),"npc", responseDTO.getResponse())
        );

        return new ResponsePlayDTO.EventDTO(
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
                AnalyzedEvent.DIALOG.getDescription(),
                "",
                responseDTO.getResponse()
        );
    }



    //캐릭터 상태 저장
    public ResponsePlayDTO.EndResultDTO endBattle(RequestPlayDTO.BattleResultDTO battleResultDTO) {

        // 플레이어 상태 리스트 가져오기
        List<RequestPlayDTO.UserStatusDTO> userSatusList = battleResultDTO.userSatusList();

        // 현재 진행 중인 이야기에서 플레이어와 NPC 리스트 가져오기
        RunningStoryDTO runningStoryDTO = runningService.getRunningStoryById(battleResultDTO.roomNum());
        List<PlayerDTO> playerList = runningStoryDTO.getPlayerList();
        List<NpcDTO> npcList = runningStoryDTO.getNpcList();

        String battleResult = "";
        if (battleResultDTO.isBattleWon()) {
            battleResult = "전투결과, 플레이어 측이 승리하였다.";
        }else {
            battleResult = "전투결과, 플레이어 측이 패배하였다.";
        }
        dialogArchiveService.save(
                new CreateDialogArchiveDTO(battleResultDTO.roomNum(), "system", battleResult)
        );

        // 플레이어 상태 업데이트 및 사망 판정
        for (RequestPlayDTO.UserStatusDTO status : userSatusList) {
            for (PlayerDTO player : playerList) {
                if (Objects.equals(status.userCode(), player.getUserCode())) {
                    // 체력 저장
                    player.setHealthPoints(status.healthPoint());
                    // 상태 저장
                    player.setStatus(status.status());

                    // 사망 판정
                    if (status.healthPoint() < 1) {
                        status.status().add("죽음");
                        String nickName = player.getNickname();

                        // Archive 생성
                        dialogArchiveService.save(
                                new CreateDialogArchiveDTO(battleResultDTO.roomNum(), "system", nickName + " 사망하였다.")
                        );
                    }
                    break; // 해당 플레이어를 찾았으므로 루프 종료
                }
            }
        }

        // NPC 상태 업데이트 및 사망 판정
        RequestPlayDTO.NpcStatusDTO npcStatusDTO = battleResultDTO.npcStatusDTO();
        if (npcStatusDTO != null) {
            for (NpcDTO npc : npcList) {
                if (Objects.equals(npc.getScenarioAvatarId(), npcStatusDTO.scenarioAvatarId())) {
                    // 체력 저장
                    npc.setHealthPoints(npcStatusDTO.healthPoint());
                    // 상태 저장
                    npc.setStatus(npcStatusDTO.status());

                    // 사망 판정
                    if (npcStatusDTO.healthPoint() < 1) {
                        npc.getStatus().add("죽음");
                        String npcName = npc.getAvatarName();

                        // Archive 생성
                        dialogArchiveService.save(
                                new CreateDialogArchiveDTO(battleResultDTO.roomNum(), "system", npcName + " 사망하였다.")
                        );
                    }
                    break; // 해당 NPC를 찾았으므로 루프 종료
                }
            }
        }

        // 업데이트된 플레이어 및 NPC 리스트 저장
        runningStoryDTO.setPlayerList(playerList);
        runningStoryDTO.setNpcList(npcList);
        runningService.updateRunningStory(runningStoryDTO);

        return endDialog(battleResultDTO.roomNum());
    }




    //히스토리 생성, 퀘스트 판정, 대화 로그 전부 넘기기, 삭제
    public ResponsePlayDTO.EndResultDTO endDialog(Integer roomNum) {
        RoomArchiveDTO archiveDTO = dialogArchiveService.findRoomArchiveByRoomNum(roomNum);

        EndDialogDTO endDialogDTO = aiService.endDialog(archiveDTO).block();
        if (endDialogDTO == null || endDialogDTO.getHistory() == null || endDialogDTO.getHistory().isEmpty()) {
            log.info("히스토리가 생성되지 않음");
            throw new ApplicationException(ErrorCode.CANNOT_HANDLE_EVENT);
        }

        historyService.createHistory(
                new RequestHistoryDTO.createDTO(
                        roomNum,
                        endDialogDTO.getHistory()
                )
        );


        dialogArchiveService.delete(roomNum);

        return new ResponsePlayDTO.EndResultDTO(
                endDialogDTO.getHistory(),
                endDialogDTO.getIsMainQuestClear(),
                endDialogDTO.getClearSubQuestNum()
        );
    }
}
