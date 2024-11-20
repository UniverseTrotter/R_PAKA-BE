package com.ohgiraffers.r_pakabe.flow.logic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gm")
@Tag(name = "SSE 컨트롤러", description = "SSE 방식으로 진행상황을 받을 수 있는 api 입니다.")
public class GMController {



    @Operation(summary = "GM 과 연결", description = "SSE 구독을 시작합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/sse")
    public Flux<String> streamEvents() {
        // 1초 간격으로 시간 정보를 스트리밍
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "현재 시간: " + LocalTime.now());
    }

}
