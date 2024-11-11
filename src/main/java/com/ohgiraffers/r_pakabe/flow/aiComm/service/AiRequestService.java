package com.ohgiraffers.r_pakabe.flow.aiComm.service;

import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiDtoMapper;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DialogAiStartDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.RoomAiStartDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogStartResponseDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class AiRequestService {

    private final AiConnectionService connectionService;
    private final AiDtoMapper mapper;
    private final View error;


    public void startPlay(RunningStoryDTO runningDTO) {
        RoomAiStartDTO startDTO = mapper.runningToStart(runningDTO);
        startDTO.setTitle(runningDTO.getScenarioTitle());
        startDTO.setSessionID(runningDTO.getRoomNum());

        log.info("Start Play : {}", startDTO);
        Mono<String> response = connectionService.postData(startDTO, "/startScenario");

        // 구독 설정
        response.subscribe(
                result -> log.info("Received Response: {}", result),  // 데이터가 도착하면 처리
                error -> log.error("Error: {}",error.getMessage()),   // 에러가 발생하면 처리
                () -> log.info("Request Completed!")                // 완료되면 처리
        );
    }

    @SneakyThrows
    public Mono<DialogStartResponseDTO> startDialog(DialogAiStartDTO startDialogDTO) {
        log.info("Start Dialog : {}", startDialogDTO);

        return connectionService.postData(startDialogDTO, "/startDailog")
                .map(result -> {
                    log.info("Received DialogStartResponseDTO: {}", result);
                    return new DialogStartResponseDTO(result);
                })
                .onErrorMap(Exception::new);
    }
//
//    private void throwException(Throwable throwable) throws Exception {
//        throw new Exception(throwable);
//    }
}
