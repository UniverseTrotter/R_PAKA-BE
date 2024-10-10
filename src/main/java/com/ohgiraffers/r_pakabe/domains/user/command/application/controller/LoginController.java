package com.ohgiraffers.r_pakabe.domains.user.command.application.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "유저 API", description = "로그인, 회원가입 기능을 제공하는 API")
public class LoginController {



    @Operation(summary = "로그인", description = "로그인 정보를 받고 일치하면 user 코드를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 로그인 하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @GetMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> loginUser(){
        return ResponseEntity.ok().build();
    }
}
