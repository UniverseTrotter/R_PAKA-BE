package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.controller;

import com.ohgiraffers.r_pakabe.common.RecordNullChecker;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.RequestAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.service.ScenarioAvatarAppService;
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
@RequestMapping("/scenario/avatar")
@RestController
@Tag(name = "시나리오 아바타", description = "시나리오에 사용되는 아바타 데이터")
public class ScenarioAvatarController {

    private final ScenarioAvatarAppService scenarioAvatarAppService;

    @Operation(summary = "모든 아바타 리스트", description = "등록된 모든 시나리오 아바타를 리스트로 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAllAvatars() {
        List<ScenarioAvatarDTO> avatarDTOS = scenarioAvatarAppService.finAllAvatars();
        return ResponseEntity.ok(avatarDTOS);
    }




    @Operation(summary = "번호로 아바타 찾기", description = "번호로 아바타 객체를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/get")
    public ResponseEntity<?> findScenarioAvatarById(
            @ModelAttribute RequestAvatarDTO.AvatarIdDTO avatarIdDTO) throws Exception {
        RecordNullChecker.hasNullFields(avatarIdDTO);
        Integer avatarId = avatarIdDTO.avatarId();
        ScenarioAvatarDTO scenarioAvatarDTO = scenarioAvatarAppService.findScenarioAvatarById(avatarId);
        return ResponseEntity.ok(scenarioAvatarDTO);
    }

    @Operation(summary = "아바타 생성", description = "새 아바타를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createScenarioAvatar(
            @RequestBody RequestAvatarDTO.CreateAvatarDTO scenarioAvatarDTO) throws Exception {
        RecordNullChecker.hasNullFields(scenarioAvatarDTO);
        ScenarioAvatarDTO avatarDTO = scenarioAvatarAppService.createAvatar(scenarioAvatarDTO);
        return ResponseEntity.ok(avatarDTO);
    }


    @Operation(summary = "아바타 업데이트", description = "기존 아바타를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateScenarioAvatar(
            @RequestBody ScenarioAvatarDTO scenarioAvatarDTO)throws Exception {
        RecordNullChecker.hasNullFields(scenarioAvatarDTO);
        ScenarioAvatarDTO avatarDTO = scenarioAvatarAppService.updateAvatar(scenarioAvatarDTO);
        return ResponseEntity.ok(avatarDTO);
    }

    @Operation(summary = "아바타 삭제", description = "기존 아바타를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteScenarioAvatar (
            @RequestBody RequestAvatarDTO.AvatarIdDTO avatarIdDTO) throws Exception {
        RecordNullChecker.hasNullFields(avatarIdDTO);
        Integer avatarId = avatarIdDTO.avatarId();
        scenarioAvatarAppService.deleteAvatar(avatarId);
        return ResponseEntity.ok().build();
    }

}
