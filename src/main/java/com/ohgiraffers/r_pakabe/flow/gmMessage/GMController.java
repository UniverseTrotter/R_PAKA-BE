package com.ohgiraffers.r_pakabe.flow.gmMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/gm")
@Tag(name = "SSE 컨트롤러", description = "SSE 방식으로 진행상황을 받을 수 있는 api 입니다.")
public class GMController {

    private final RoomMessageService msgService;

    @Operation(summary = "GM 과 연결", description = "SSE 구독을 시작합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents(@RequestParam Integer roomId) {
        log.info("sse 구독 시작 부분");
        Sinks.Many<String> sink = msgService.getSinkForRoom(roomId);
        return sink.asFlux()
                .doOnCancel(() -> {
                    log.info("Connection cancelled by client");
                    msgService.removeRoomSink(roomId);
                })
                .onErrorResume(IOException.class, e -> {
                    log.info("Client disconnected: {}", e.getMessage());
                    return Flux.empty();
                })
                ;
    }

    @Operation(summary = "GM 과 연결 종료")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping(path = "/disconnect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<?> end(@RequestParam Integer roomId) {
        log.info("방 종료");
        msgService.removeRoomSink(roomId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "GM이 TEST 메세지 발송")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping(path = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<?> msgTest(@RequestParam Integer roomId) {
        msgService.emitToRoom(roomId,"Test Message : 테스트 멧세쥐 멧밭쥐 코끼리땃쥐");
        return ResponseEntity.ok().build();
    }

}
