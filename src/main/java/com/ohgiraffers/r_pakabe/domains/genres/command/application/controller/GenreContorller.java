package com.ohgiraffers.r_pakabe.domains.genres.command.application.controller;


import com.ohgiraffers.r_pakabe.common.RecordNullChecker;
import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.NullfieldException;
import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreRequestDTO;
import com.ohgiraffers.r_pakabe.domains.genres.command.application.service.GenreAppService;
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
@RequestMapping("/genre")
@Tag(name = "장르", description = "장르 생성, 삭제 api")
public class GenreContorller {
    private final GenreAppService genreAppService;


    @Operation(summary = "장르 리스트", description = "등록된 장르 전체를 리스트로 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getAllGenre() {
        List<GenreDTO> genrelist = genreAppService.findAllGenre();
        return ResponseEntity.ok().body(genrelist);
    }

    @Operation(summary = "번호로 장르 찾기", description = "번호로 장르 객체를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/get/id")
    public ResponseEntity<?> findGenreById(@ModelAttribute GenreRequestDTO.GenreIdDTO genreIdDTO) throws Exception {
        RecordNullChecker.hasNullFields(genreIdDTO);
        GenreDTO genreDTO = genreAppService.findGenreById(genreIdDTO.genreId());
        return ResponseEntity.ok().body(genreDTO);
    }

    @Operation(summary = "장르명으로 찾기", description = "이름으로 장르 객체를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/get/name")
    public ResponseEntity<?> findGenreByName(@ModelAttribute GenreRequestDTO.GenreNameDTO genreNameDTO) throws Exception {
        RecordNullChecker.hasNullFields(genreNameDTO);
        GenreDTO genreDTO = genreAppService.findGenreByName(genreNameDTO.genreName());
        return ResponseEntity.ok().body(genreDTO);
    }



    @Operation(summary = "장르 생성", description = "새 장르를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createGenre(@RequestParam GenreRequestDTO.GenreNameDTO genreNameDTO) throws Exception {
        RecordNullChecker.hasNullFields(genreNameDTO);
        genreAppService.createGenre(genreNameDTO.genreName());
        log.info("Create Tag : {}", genreNameDTO.genreName());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "(관리용) 장르 삭제", description = "장르를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/delete")
    public ResponseEntity<?> deleteGenre(@RequestParam GenreRequestDTO.GenreIdDTO genreIdDTO) throws Exception {
        RecordNullChecker.hasNullFields(genreIdDTO);
        genreAppService.deleteGenre(genreIdDTO.genreId());
        log.info("Delete Tag : {}", genreIdDTO.genreId());
        return ResponseEntity.ok().build();
    }

}
