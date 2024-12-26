package com.ohgiraffers.r_pakabe.flow.logic.controller;

import com.ohgiraffers.r_pakabe.flow.logic.dto.RequestPlayDTO;
import com.ohgiraffers.r_pakabe.flow.logic.dto.ResponsePlayDTO;
import com.ohgiraffers.r_pakabe.flow.logic.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
@Tag(name = "플레이 방 컨트롤러", description = "방 생성 삭제")
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "플레이 시작", description = ".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/start")
    public ResponseEntity<?> start(@RequestBody RequestPlayDTO.roomStartDTO roomStartDTO) {
        roomService.startRoom(roomStartDTO);
        ResponsePlayDTO.RoomOpeningDTO openingMsg =
                roomService.getOpeningMessage(new RequestPlayDTO.RoomNumDTO(roomStartDTO.roomNum()));
        return ResponseEntity.ok(openingMsg);
    }

//    @PostMapping("/opening")
//    public ResponseEntity<?> open(@RequestBody RequestPlayDTO.RoomNumDTO roomNumDTO) {
//        return ResponseEntity.ok(roomService.getOpeningMessage(roomNumDTO));
//    }


    @Operation(summary = "진행중인 세션 리스트.", description = ".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/list")
    public ResponseEntity<?> list() {
        List<ResponsePlayDTO.RunningListDTO> runningList = roomService.getRunningList();
        return ResponseEntity.ok(runningList);
    }


    @Operation(summary = "진행중인 세션을 종료합니다.", description = "룸을 종료하고 데이터를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/end")
    public ResponseEntity<?> end(@RequestBody RequestPlayDTO.RoomNumDTO roomNumDTO) {
        roomService.endRoom(roomNumDTO.roomNum());
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "후처리.", description = "제대로 저장되지 않은 진행 데이터를 정리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 처리 되었습니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 예러")
    })
    @PostMapping("/trim")
    public ResponseEntity<?> trim(@RequestBody RequestPlayDTO.RoomNumDTO roomNumDTO) {
        roomService.trimRoomData(roomNumDTO.roomNum());
        return ResponseEntity.ok().build();
    }



}
