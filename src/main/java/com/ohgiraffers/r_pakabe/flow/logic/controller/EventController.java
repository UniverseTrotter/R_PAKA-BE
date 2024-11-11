package com.ohgiraffers.r_pakabe.flow.logic.controller;

import com.ohgiraffers.r_pakabe.flow.logic.dto.ResponsePlayDTO;
import com.ohgiraffers.r_pakabe.flow.logic.service.DiceService;
import com.ohgiraffers.r_pakabe.flow.logic.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/progress")
@Tag(name = "플레이 진행 컨트롤러", description = "대사, 전투, (탐색)")
public class EventController {

    private final EventService eventService;
    private final DiceService diceService;

    @Operation(summary = "대화 시작", description = ".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/start")
    public ResponseEntity<?> start(){
        return null;
    }


    @Operation(summary = "다이스 판정", description = ".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/dice")
    public ResponseEntity<?> dice(){
        return null;
    }


    @Operation(summary = "전투 종료", description = ".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/battle")
    public ResponseEntity<?> battleEnd(){
        return null;
    }




    @Operation(summary = "대화를 종료합니다.", description = ".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/end")
    public ResponseEntity<?> end(){
        return null;
    }


    @Operation(summary = "다이스 롤", description = "주사위 2개를 굴립니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/dice")
    public ResponseEntity<?> diceRoll(){
        ResponsePlayDTO.DiceRollDTO diceRollDTO = diceService.rollDice();
        return ResponseEntity.ok(diceRollDTO);
    }




}
