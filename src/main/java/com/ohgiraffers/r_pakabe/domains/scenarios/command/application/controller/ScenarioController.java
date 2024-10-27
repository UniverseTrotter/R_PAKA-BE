package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.controller;

import com.ohgiraffers.r_pakabe.common.RecordNullChecker;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.RequestScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.ScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.service.ScenarioAppService;
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
@RequestMapping("/scenario")
@Tag(name = "시나리오", description = "시나리오 조회, 변경 API")
public class ScenarioController {

    private final ScenarioAppService scenarioAppService;


    @Operation(summary = "테스트) 시나리오 모두 불러오기", description = "모든 시나리오를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAllScenario() {
        log.info("get All Scenario");
        List<ScenarioDTO> scenarioList = scenarioAppService.getAllScenarioList();
        return ResponseEntity.ok().body(scenarioList);
    }


    @Operation(summary = "시나리오 불러오기", description = "리스트에서 선택된 코드로 시나리오를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "해당 시나리오가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/detail")
    public ResponseEntity<?> loadScenarioDetail(Long scenarioCode) {
        ScenarioDTO dto = scenarioAppService.getScenario(scenarioCode);
        return ResponseEntity.ok(dto);
    }


    @Operation(summary = "테스트) 시나리오 만들기", description = "새로운 시나리오를 등록합니다.<br>" +
            "<h4>업데이트와 차이는 scenarioCode의 유무 입니다.</h4>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createScenario(@RequestBody RequestScenarioDTO.CreateScenarioDTO scenarioDTO) throws Exception {
        log.info("createScenario {}", scenarioDTO);
        RecordNullChecker.hasNullFields(scenarioDTO);
        scenarioAppService.createScenario(scenarioDTO);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "작업중) 시나리오 업데이트", description = "기존 시나리오를 업데이트합니다.<br>" +
            "<h4>scenarioCode를 필요로 합니다.</h4>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "해당 시나리오가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateScenario(@RequestBody ScenarioDTO scenarioDTO) {
        log.info("updateScenario {}", scenarioDTO);
//        return ResponseEntity.ok().build();        //통신 테스트용
        if (scenarioDTO.scenarioCode() == null) {
            return ResponseEntity.badRequest().build();
        }
        scenarioAppService.updateScenario(scenarioDTO);
        return ResponseEntity.ok().build();
    }


}
