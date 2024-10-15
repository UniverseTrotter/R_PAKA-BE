package com.ohgiraffers.r_pakabe.domains.user.command.application.controller;


import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserRequestDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.service.UserAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Tag(name = "로그인 API", description = "로그인 기능을 제공하는 API, jwt 넣으려 분리 했었음")
public class LoginController {

    private final UserAppService userAppService;


    public LoginController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @Operation(summary = "로그인", description = "로그인 정보를 받고 일치하면 user 코드를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 로그인 하였습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 잘못 되었습니다."),
            @ApiResponse(responseCode = "401", description = "등록되지 않은 유저입니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(UserRequestDTO.LoginDTO loginDTO) {
        System.out.println(loginDTO);
        if (loginDTO.userId() == null || loginDTO.userId().isEmpty()) {
            throw new ApplicationException(ErrorCode.ID_IS_EMPTY);
        }else if (loginDTO.password() == null || loginDTO.password().isEmpty()) {
            throw new ApplicationException(ErrorCode.PW_IS_EMPTY);
        }

        UserResponseDTO.authDTO authDTO = userAppService.userLogin(loginDTO);
        return ResponseEntity.ok().body(authDTO);
    }



}
