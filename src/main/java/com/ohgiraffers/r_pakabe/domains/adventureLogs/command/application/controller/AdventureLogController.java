package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.controller;

import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.AdventureLogDTO;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.ResponseAdventureDTO;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.service.AdventureLogAppService;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.service.SceneHistoryAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
@Tag(name = "모험담", description = "모험 결과 관련 api")
public class AdventureLogController {

    private final AdventureLogAppService appService;



    @Operation(summary = "모험담 리스트", description = "모든 모험담의 리스트를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getList(){
        ResponseAdventureDTO.LogList adventures = appService.getAllList();
        return ResponseEntity.ok(adventures);
    }

    @Operation(summary = "특정 모험담 불러오기", description = "모험담의 상세내용을 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/get")
    public ResponseEntity<?> findById(Long id){
        AdventureLogDTO adventureLog = appService.findById(id);
        return ResponseEntity.ok(adventureLog);
    }
}
