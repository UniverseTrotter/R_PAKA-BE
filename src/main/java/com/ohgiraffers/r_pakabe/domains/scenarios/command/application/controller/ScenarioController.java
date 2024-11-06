package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.controller;

import com.ohgiraffers.r_pakabe.common.RecordNullChecker;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.RequestScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.ResponseScenarioDTO;
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



    @Operation(summary = "시나리오 리스트 불러오기", description = "모든 시나리오의 id와 제목을 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAllScenariosId() {
        List<ResponseScenarioDTO.AllScenarioIDDTO> ScenarioDTOList = scenarioAppService.getAllScenariosId();
        return ResponseEntity.ok(ScenarioDTOList);
    }



    @Operation(summary = "시나리오 객체 모두 불러오기", description = "모든 시나리오 객체를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAllScenario() {
        List<ScenarioDTO> scenarioList = scenarioAppService.getAllScenarioList();
        return ResponseEntity.ok(scenarioList);
    }


    @Operation(summary = "시나리오 불러오기", description = "리스트에서 선택된 코드로 시나리오 객체를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "해당 시나리오가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/detail")
    public ResponseEntity<?> loadScenarioDetail(@ModelAttribute RequestScenarioDTO.ScenarioIdDTO scenarioIdDTO) throws Exception {
        RecordNullChecker.hasNullFields(scenarioIdDTO);
        Long scenarioCode = scenarioIdDTO.scenarioCode();
        ScenarioDTO dto = scenarioAppService.getScenario(scenarioCode);
        return ResponseEntity.ok(dto);
    }



    @Operation(summary = "시나리오 만들기", description = "새로운 시나리오를 등록합니다.<br>" +
            "<h4>업데이트와 차이는 scenarioCode 의 유무 입니다.</h4>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createScenario(@RequestBody RequestScenarioDTO.CreateScenarioDTO scenarioDTO) throws Exception {
//        RecordNullChecker.hasNullFields(scenarioDTO);     //장르와 태그 부재로 null 체크 안하게
        ScenarioDTO created = scenarioAppService.createScenario(scenarioDTO);
        return ResponseEntity.ok(created);
    }


    @Operation(summary = "시나리오 업데이트", description = "기존 시나리오를 업데이트합니다." +
            "<h4>scenarioCode 를 필요로 합니다.</h4>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateScenario(@RequestBody RequestScenarioDTO.NewScenarioDTO scenarioDTO) throws Exception {
        RecordNullChecker.hasNullFields(scenarioDTO);
        ScenarioDTO updated = scenarioAppService.updateScenario(scenarioDTO);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "시나리오 삭제", description = "기존 시나리오를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteScenario(@ModelAttribute RequestScenarioDTO.ScenarioIdDTO scenarioIdDTO) throws Exception {
        RecordNullChecker.hasNullFields(scenarioIdDTO);
        Long scenarioCode = scenarioIdDTO.scenarioCode();
        scenarioAppService.deleteScenario(scenarioCode);
        return ResponseEntity.ok().build();
    }












    @Operation(summary = "테스트) 시나리오 만들기", description = "새로운 시나리오를 등록합니다.<br>" +
            "<h4>업데이트와 차이는 scenarioCode의 유무 입니다.</h4>",deprecated = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/upload")
    public ResponseEntity<?> uploadScenario(@RequestBody RequestScenarioDTO.UploadScenarioDTO scenarioDTO) throws Exception {
        RecordNullChecker.hasNullFields(scenarioDTO);
        ScenarioDTO created = scenarioAppService.createScenario(scenarioDTO);
        return ResponseEntity.ok(created);
    }


    @Operation(summary = "작업중) 시나리오 업데이트", description = "기존 시나리오를 업데이트합니다.<br>" +
            "<h4>scenarioCode를 필요로 합니다.</h4>", deprecated = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "해당 시나리오가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PutMapping("/renew")
    public ResponseEntity<?> renewScenario(@RequestBody ScenarioDTO scenarioDTO) {
        log.info("updateScenario {}", scenarioDTO);
//        return ResponseEntity.ok().build();        //통신 테스트용
        if (scenarioDTO.scenarioCode() == null) {
            return ResponseEntity.badRequest().build();
        }
        scenarioAppService.renewScenario(scenarioDTO);
        return ResponseEntity.ok().build();
    }


}
