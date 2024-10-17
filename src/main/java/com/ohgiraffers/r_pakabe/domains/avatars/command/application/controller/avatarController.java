package com.ohgiraffers.r_pakabe.domains.avatars.command.application.controller;

import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.service.AvatarAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/avatar")
public class avatarController {

    private final AvatarAppService avatarAppService;

    public avatarController(AvatarAppService avatarAppService) {
        this.avatarAppService = avatarAppService;
    }


    @Operation(summary = "회원 아바타 정보", description = "회원코드로 아바타 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 반환 되었습니다."),
            @ApiResponse(responseCode = "400", description = "해당 유저에게 아바타가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/user")
    public ResponseEntity<?> getUserAvatar(Long userCode){
        UserResponseDTO.userAvatarDTO avatarDTO = avatarAppService.getUserAvatar(userCode);
        return ResponseEntity.ok(avatarDTO);
    }

}
