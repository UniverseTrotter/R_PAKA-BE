package com.ohgiraffers.r_pakabe.domains.user.command.application.controller;

import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserRequestDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.service.UserAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "유저 API", description = "회원가입, 유저 정보 조회, 변경 API")
public class UserController {

    UserAppService userAppService;

    public UserController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }


    @Operation(summary = "회원가입", description = "회원가입에 필요한 정보를 입력받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 회원가입 되었습니다."),
            @ApiResponse(responseCode = "400", description = "중복되는 ID가 있습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/register")
    public ResponseEntity<?> userRegister(UserRequestDTO.RegisterDTO registerDTO) {
        userAppService.userRegister(registerDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 정보 조회", description = "회원코드로 회원 id와 닉네임을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/detail")
    public ResponseEntity<?> userRegister(Long userCode) {
        return ResponseEntity.ok().body(userAppService.findUser(userCode));
    }





    @Operation(summary = "패스워드 변경"/*, deprecated = true*/)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/password")
    public ResponseEntity<?> changeUserPW(UserRequestDTO.UserUpdateDTO updateDTO) {
        userAppService.changeUserPW(updateDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "닉네임 변경"/*, deprecated = true*/)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "400", description = "닉네임이 기존과 다르지 않습니다"),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/nickname")
    public ResponseEntity<?> changeUserNickName(UserRequestDTO.RegisterDTO registerDTO) {
        userAppService.changeUserNickName(registerDTO.userId(), registerDTO.nickName());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 탈퇴"/*, deprecated = true*/)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/unregister")
    public ResponseEntity<?> unregister(String userId) {
        userAppService.unregisterUser(userId);
        return ResponseEntity.ok().build();
    }
}
