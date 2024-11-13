package com.ohgiraffers.r_pakabe.domains.user.command.application.controller;

import com.ohgiraffers.r_pakabe.common.RecordNullChecker;
import com.ohgiraffers.r_pakabe.common.error.NullfieldException;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserRequestDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.service.UserAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "유저", description = "회원가입, 유저 정보 조회, 변경 API")
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
    public ResponseEntity<?> userRegister(UserRequestDTO.RegisterDTO registerDTO) throws Exception{
        RecordNullChecker.hasNullFields(registerDTO);
        UserResponseDTO.authDTO authDTO = userAppService.userRegister(registerDTO);
        return ResponseEntity.ok(authDTO);
    }

    @Operation(summary = "회원 정보 조회", description = "회원코드로 회원 id와 닉네임을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/detail")
    public ResponseEntity<?> getUserDetail(Long userCode) {
        return ResponseEntity.ok().body(userAppService.findUser(userCode));
    }





    @Operation(summary = "패스워드 변경"/*, deprecated = true*/)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PutMapping("/password")
    public ResponseEntity<?> changeUserPW(UserRequestDTO.UserUpdateDTO updateDTO) {
        if (updateDTO.password() == null || updateDTO.password().isEmpty()) {
            throw new NullfieldException("비밀번호가 입력되지 않았습니다.");
        }
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
    @PutMapping("/nickname")
    public ResponseEntity<?> changeUserNickName(UserRequestDTO.UserUpdateDTO updateDTO) {
        if (updateDTO.nickname() == null || updateDTO.nickname().isEmpty()) {
            throw new NullfieldException("닉네임이 입력되지 않았습니다.");
        }
        userAppService.changeUserNickName(updateDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 탈퇴"/*, deprecated = true*/)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @DeleteMapping("/unregister")
    public ResponseEntity<?> unregister(Long userCode) {
        userAppService.unregisterUser(userCode);
        return ResponseEntity.ok().build();
    }
}
