package com.ohgiraffers.r_pakabe.domains.avatars.command.application.controller;

import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.service.AvatarAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


//@Tag(name = "프리셋 아바타", description = "시나리오 아바타 관련 api")
@RestController
public class NpcAvatarController {

    private final AvatarAppService avatarAppService;

    public NpcAvatarController(AvatarAppService avatarAppService) {
        this.avatarAppService = avatarAppService;
    }

//    @Operation(deprecated = true, summary = "관리용 : npc 아바타 정보", description = "npc용 아바타 정보를 업로드합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공적으로 반환 되었습니다."),
//            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
//    })
//    @GetMapping("/npc")
//    public ResponseEntity<?> getNPCAvatar(Long userCode){
//        UserAvatarDTO avatarDTO = avatarAppService.getUserAvatar(userCode);
//        return ResponseEntity.ok(avatarDTO);
//    }
//
//    @Operation(deprecated = true, summary = "관리용 : npc 아바타 업로드", description = "npc용 아바타 정보를 업로드합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공적으로 반환 되었습니다."),
//            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
//    })
//    @PostMapping("/npc/upload")
//    public ResponseEntity<?> uploadNPCAvatar(Long userCode){
//        UserAvatarDTO avatarDTO = avatarAppService.getUserAvatar(userCode);
//        return ResponseEntity.ok(avatarDTO);
//    }
//
//    @Operation(deprecated = true, summary = "관리용 : npc 아바타 리스트", description = "모든 npc용 아바타 정보를 가져옵니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공적으로 반환 되었습니다."),
//            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
//    })
//    @GetMapping("/npc/all")
//    public ResponseEntity<?> getNPCAvatarList(Long userCode){
//        UserAvatarDTO avatarDTO = avatarAppService.getUserAvatar(userCode);
//        return ResponseEntity.ok(avatarDTO);
//    }
}
