package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.controller;


import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.RequestHistoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.ResponseHistoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.SceneHistoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.service.SceneHistoryAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
@Tag(name = "플레이 히스토리", description = "진행기록 관련 api")
public class SceneHistoryController {

    private final SceneHistoryAppService appService;


    @Operation(summary = "모든 히스토리", description = "모든 시나리오의 리스트를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        ResponseHistoryDTO.HistoryListDTO dtoList = appService.getAllSceneHistory();
        return ResponseEntity.ok(dtoList);
    }


    @Operation(summary = "히스토리 조회", description = ".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam Integer roomNumber){
        ResponseHistoryDTO.HistoryListDTO dtoList = appService.getSceneHistory(roomNumber);
        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "히스토리 작성", description = ".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RequestHistoryDTO.createDTO createDTO){
        SceneHistoryDTO created = appService.createHistory(createDTO);
        System.out.println(created);
        return ResponseEntity.ok(created);
    }


    @Operation(summary = "히스토리 삭제", description = ".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Integer roomNumber){
        appService.deleteHistory(roomNumber);
        return ResponseEntity.ok().build();
    }



}
