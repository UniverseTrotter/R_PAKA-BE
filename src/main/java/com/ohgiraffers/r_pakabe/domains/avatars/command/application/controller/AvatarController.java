package com.ohgiraffers.r_pakabe.domains.avatars.command.application.controller;

import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.service.AvatarAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "유저 아바타", description = "유저 아바타 관련 api")
@RestController("/avatar")
public class AvatarController {

    private final AvatarAppService avatarAppService;

    public AvatarController(AvatarAppService avatarAppService) {
        this.avatarAppService = avatarAppService;
    }


    @Operation(summary = "유저 아바타 정보", description = "유저코드로 아바타 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 반환 되었습니다."),
            @ApiResponse(responseCode = "400", description = "해당 유저에게 아바타가 없습니다"),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/user")
    public ResponseEntity<?> getUserAvatar(Long userCode){
        UserAvatarDTO avatarDTO = avatarAppService.getUserAvatar(userCode);
        return ResponseEntity.ok(avatarDTO);
    }

    @Operation(summary = "유저 아바타 업로드", description = "유저코드로 아바타를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/user/upload")
    public ResponseEntity<?> uploadUserAvatar(UserAvatarDTO avatarDTO){

        avatarAppService.makeUserAvatar(avatarDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저 아바타 수정 ", description = "유저 회원의 아바타 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 반환 되었습니다."),
            @ApiResponse(responseCode = "400", description = "해당 유저에게 아바타가 없습니다"),
            @ApiResponse(responseCode = "401", description = "해당 유저가 없습니다"),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PutMapping("/user/update")
    public ResponseEntity<?> updateUserAvatar(UserAvatarDTO avatarDTO){
        avatarAppService.updateUserAvatar(avatarDTO);
        return ResponseEntity.ok().build();
    }



    @Operation(summary = "(관리용) 유저 아바타 리스트", description = "모든 유저 아바타 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 반환 되었습니다."),
            @ApiResponse(responseCode = "400", description = "저장된 아바타가 하나도 없습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @GetMapping("/user/all")
    public ResponseEntity<?> getUserAvatarList(){
        List<UserAvatarDTO> avatarDTO = avatarAppService.getAllList();
        return ResponseEntity.ok(avatarDTO);
    }

    @Operation(summary = "(관리용) 아바타 삭제", description = "회원 아바타 정보를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 반환 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAvatar(Long userCode){
        avatarAppService.deleteAvatar(userCode);
        return ResponseEntity.ok().build();
    }




}
