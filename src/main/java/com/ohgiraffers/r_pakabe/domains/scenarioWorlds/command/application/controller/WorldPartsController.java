package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.controller;

import com.ohgiraffers.r_pakabe.common.RecordNullChecker;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.RequestWorldDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.service.WorldPartAppService;
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
@RequestMapping("/world")
@RestController
@Tag(name = "시나리오 월드", description = "시나리오에 사용되는 맵 데이터")
public class WorldPartsController {

    private final WorldPartAppService worldPartAppService;


    @Operation(summary = "모든 월드 리스트", description = "등록된 모든 월드를 리스트로 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAllWorldParts() {
        List<WorldPartDTO> worldList = worldPartAppService.findAllWorld();
        return ResponseEntity.ok(worldList);
    }



    @Operation(summary = "번호로 월드 찾기", description = "번호로 월드 객체를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/get")
    public ResponseEntity<?> findWorldPartById(@ModelAttribute RequestWorldDTO.WorldIdDDTO worldIdDTO) throws Exception{
        RecordNullChecker.hasNullFields(worldIdDTO);
        Integer worldId = worldIdDTO.worldId();
        WorldPartDTO worldPartDTO = worldPartAppService.findWorldPartById(worldId);
        return ResponseEntity.ok(worldPartDTO);
    }

    @Operation(summary = "월드 생성", description = "새 월드를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createWorldPart(@RequestBody RequestWorldDTO.CreateWorldDTO createWorldDTO) throws Exception{
        RecordNullChecker.hasNullFields(createWorldDTO);
        WorldPartDTO worldPartDTO = worldPartAppService.createWorld(createWorldDTO);
        return ResponseEntity.ok(worldPartDTO);
    }

    @Operation(summary = "월드 업데이트", description = "기존 월드를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateWorldPart(@RequestBody RequestWorldDTO.UpdateWorldDTO worldDTO) throws Exception{
        RecordNullChecker.hasNullFields(worldDTO);
        WorldPartDTO worldPartDTO = worldPartAppService.updateWorld(worldDTO);
        return ResponseEntity.ok(worldPartDTO);
    }

    @Operation(summary = "월드 삭제", description = "기존 월드를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteWorldPart(@RequestBody RequestWorldDTO.WorldIdDDTO worldIdDDTO) throws Exception{
        RecordNullChecker.hasNullFields(worldIdDDTO);
        Integer worldId = worldIdDDTO.worldId();
        worldPartAppService.deleteWorld(worldId);
        return ResponseEntity.ok().build();
    }






}
