package com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.controller;

import com.ohgiraffers.r_pakabe.common.RecordNullChecker;
import com.ohgiraffers.r_pakabe.common.error.NullfieldException;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto.ScenarioTagDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto.TagRequestDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.service.TagAppService;
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
@RequestMapping("/tag")
@Tag(name = "시나리오 태그", description = "태그 생성, 삭제 관련 api")
public class SenarioTagController {

    private final TagAppService tagAppService;

    @Operation(summary = "모든 태그 리스트", description = "등록된 모든 태그를 리스트로 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAllTags() {
        List<ScenarioTagDTO> tagDTOList = tagAppService.getAllTags();
        return ResponseEntity.ok(tagDTOList);
    }


    @Operation(summary = "(관리용) 번호로 태그 찾기", description = "번호로 태그 객체를 반환합니다.<br>" +
            "기능은 하지만 관리용 이외에는 추천 하지 않습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/get/id")
    public ResponseEntity<?> findTagById(@ModelAttribute TagRequestDTO.TagIdDTO tagIdDTO) throws Exception {
        RecordNullChecker.hasNullFields(tagIdDTO);
        Integer tagId = tagIdDTO.tagId();
        ScenarioTagDTO tagDTO = tagAppService.findTagById(tagId);
        return ResponseEntity.ok(tagDTO);
    }

    @Operation(summary = "태그 찾기", description = "태그명으로 태그 객체를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/get/name")
    public ResponseEntity<?> findTagByName(@ModelAttribute TagRequestDTO.TagNameDTO tagNameDTO) throws Exception {
        RecordNullChecker.hasNullFields(tagNameDTO);
        ScenarioTagDTO tagDTO = tagAppService.findTagByName(tagNameDTO.tagName());
        return ResponseEntity.ok(tagDTO);
    }



    @Operation(summary = "태그 생성", description = "새 태그를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createTag(@RequestBody TagRequestDTO.TagNameDTO tagNameDTO) throws Exception {
        RecordNullChecker.hasNullFields(tagNameDTO);
        String tagName = tagNameDTO.tagName();
        tagAppService.creatTag(tagName);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "(관리용) 태그 삭제", description = "태그를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTag(@RequestBody TagRequestDTO.TagIdDTO tagIdDTO) throws Exception {
        RecordNullChecker.hasNullFields(tagIdDTO);
        Integer tagId = tagIdDTO.tagId();
        tagAppService.deleteTag(tagId);
        return ResponseEntity.ok().build();
    }

}
