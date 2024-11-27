package com.ohgiraffers.r_pakabe.flow.aiComm.service;

import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiDtoMapper;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.*;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogAnalyzedDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogResponseDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogStartResponseDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.EndDialogDTO;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto.RoomArchiveDTO;
import com.ohgiraffers.r_pakabe.flow.logic.dto.RequestPlayDTO;
import com.ohgiraffers.r_pakabe.flow.logic.dto.ResponsePlayDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class AiRequestService {

    private final AiConnectionService connectionService;
    private final AiDtoMapper mapper;


    public void startPlay(RunningStoryDTO runningDTO) {
        RoomAiStartDTO startDTO = mapper.runningToStart(runningDTO);
        startDTO.setTitle(runningDTO.getScenarioTitle());
        startDTO.setRoomNum(runningDTO.getRoomNum());

        log.info("Start Play title : {} with RoomNum : {}", startDTO.getTitle(), startDTO.getRoomNum());
        String response = connectionService.startScenario(startDTO).doOnError(
                error -> {
                    log.error(error.getMessage());
                }
        ).block();

        log.info("Start Play response: {}", response);

    }

    public Mono<ResponsePlayDTO.RoomOpeningDTO> createGreeting(RequestPlayDTO.RoomNumDTO roomNumDTO) {
        return connectionService.createGreeting(roomNumDTO)
                .doOnNext(result -> log.info("Received Dialog create Greeting : {}", result))
                .onErrorMap(Exception::new);
    }


    public Mono<DialogStartResponseDTO> startDialog(DialogAiStartDTO startDialogDTO) {
        log.info("Start Dialog : {}", startDialogDTO);

        return connectionService.startDialog(startDialogDTO)
                .doOnNext(result -> log.info("Received Dialog Start Response : {}", result))
                .onErrorMap(Exception::new);
    }

    public Mono<DialogAnalyzedDTO> analyzeDialog(RequestPlayDTO.DialogSendDTO dialogSendDTO) {
        RequestAnalyzeDTO analyzeDTO = mapper.sendDtoToAnalyzeDto(dialogSendDTO);
        return connectionService.analyseDialog(analyzeDTO)
                .map(response -> {
                    log.info("Received Dialog Analyzed : {}", response);
                    return new DialogAnalyzedDTO(
                            response.getEvent(),
                            response.getBonus(),
                            response.getGmMsg()
                    );
                })
                .onErrorMap(Exception::new);
    }

    public Mono<DialogResponseDTO> requestDialog(RequestAnalyzeDTO requestAnalyzeDTO) {
        return connectionService.requestDialog(requestAnalyzeDTO)
                .doOnNext(result-> log.info("Received Dialog Response : {}", result))
                .onErrorMap(Exception::new);

    }

    public Mono<DialogResponseDTO> responseDice(DiceDialogDTO requestDto) {
        return connectionService.responseDice(requestDto)
                .doOnNext(result-> log.info("Received Dice Dialog Response : {}", result))
                .onErrorMap(Exception::new);

    }

    public Mono<EndDialogDTO> endDialog(RoomArchiveDTO archiveDTO) {
        RequestEndDTO endDTO = mapper.archiveToEndDto(archiveDTO);
        return connectionService.endDialog(endDTO)
                .doOnNext(result-> log.info("Received End Dialog Response : {}", result))
                .onErrorMap(Exception::new);

    }

    public Mono<Object> endScenario(RequestPlayDTO.RoomNumDTO roomNumDTO) {
        return connectionService.endScenario(roomNumDTO)
                .doOnNext(result-> log.info("Received End Scenario Response : {}", result))
                .onErrorMap(Exception::new);
    }
}
