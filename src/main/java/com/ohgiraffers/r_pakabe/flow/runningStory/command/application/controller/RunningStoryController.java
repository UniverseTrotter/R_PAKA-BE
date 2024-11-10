package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.controller;


import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.service.RunningStoryAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test/running")
@Tag(name = "진행중인 시나리오", description = "시나리오 조회")
public class RunningStoryController {

    private final RunningStoryAppService appService;


    @Operation(summary = "진행중인 시나리오 리스트", description = "모든 시나리오의 리스트를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        List<RunningStoryDTO> dtoList = appService.getAllRunningStory();
        return ResponseEntity.ok(dtoList);
    }


    @Operation(summary = "진행중인 시나리오 정보", description = "방 번호로 진행중인 시나리오의 정보를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/get")
    public ResponseEntity<?> getScenario(@RequestParam Integer roomNum){
        RunningStoryDTO dto = appService.getRunningStoryById(roomNum);
        return ResponseEntity.ok(dto);
    }


    @Operation(summary = "진행중인 시나리오 만들기", description = "방 번호로 진행중인 시나리오의 정보를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createScenario(@RequestBody RunningStoryDTO dto){
        RunningStoryDTO createdDto = appService.createRunningStory(dto);
        return ResponseEntity.ok(createdDto);
    }


    @Operation(summary = "진행중인 시나리오 삭제", description = "해당 방 번호로 진행중인 시나리오의 정보를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/delete")
    public ResponseEntity<?> deleteScenario(@RequestParam Integer roomNum){
        appService.deleteRunningStory(roomNum);
        return ResponseEntity.ok().build();
    }

}
