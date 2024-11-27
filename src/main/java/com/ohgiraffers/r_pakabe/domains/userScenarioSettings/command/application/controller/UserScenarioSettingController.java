package com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.controller;

import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.RequestSettingDTO;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.UserScenarioSettingDTO;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.service.UserScenarioSettingAppService;
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
@RequestMapping("/scenario/user")
@RestController
@Tag(name = "시나리오 유저 세팅", description = "시나리오에 사용되는 유저 세팅")
public class UserScenarioSettingController {

    private final UserScenarioSettingAppService appService;


    @Operation(summary = "관리용) 모든 세팅 리스트", description = "등록된 모든 시나리오 세팅을 리스트로 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAllSettings(){
        List<UserScenarioSettingDTO> dtoList = appService.findAllList();
        return ResponseEntity.ok(dtoList);
    }


    @Operation(summary = "모든 세팅 리스트", description = "유저의 모든 시나리오 세팅을 리스트로 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "해당 데이터가 없습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAllSettingsByUserCode(@RequestParam Long userCode){
        List<UserScenarioSettingDTO> dtoList = appService.findAllByUserCode(userCode);
        return ResponseEntity.ok(dtoList);
    }


    @Operation(summary = "세팅 조회", description = "해당 시나리오의 유저세팅을 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "해당 데이터가 없습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/get")
    public ResponseEntity<?> getSettings(@ModelAttribute RequestSettingDTO.findOneDTO findOneDTO){
        UserScenarioSettingDTO setting = appService.findSetting(findOneDTO);
        return ResponseEntity.ok(setting);
    }


    @Operation(summary = "세팅 저장", description = "해당 시나리오의 개인 세팅을 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/upload")
    public ResponseEntity<?> uploadSettings(@RequestBody RequestSettingDTO.uploadDTO uploadDTO){
        UserScenarioSettingDTO setting = appService.uploadUserSetting(uploadDTO);
        return ResponseEntity.ok(setting);
    }


    @Operation(summary = "관리용) 세팅 삭제", description = "해당 시나리오의 개인 세팅을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteSettings(@RequestBody RequestSettingDTO.findOneDTO findOneDTO){
        appService.deleteSetting(findOneDTO);
        return ResponseEntity.ok().build();
    }



}
