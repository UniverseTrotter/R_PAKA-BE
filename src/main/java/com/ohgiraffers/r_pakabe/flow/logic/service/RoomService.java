package com.ohgiraffers.r_pakabe.flow.logic.service;


import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.service.AdventureLogAppService;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.service.AvatarAppService;
import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.service.ScenarioAvatarAppService;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.ScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.service.ScenarioAppService;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.service.UserAppService;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.RequestSettingDTO;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.UserScenarioSettingDTO;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.service.UserScenarioSettingAppService;
import com.ohgiraffers.r_pakabe.flow.aiComm.service.AiRequestService;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto.RoomArchiveDTO;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.service.DialogArchiveAppService;
import com.ohgiraffers.r_pakabe.flow.gmMessage.RoomMessageService;
import com.ohgiraffers.r_pakabe.flow.logic.dto.PlayMapper;
import com.ohgiraffers.r_pakabe.flow.logic.dto.RequestPlayDTO;
import com.ohgiraffers.r_pakabe.flow.logic.dto.ResponsePlayDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.NpcDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.PlayerDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.service.RunningStoryAppService;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.ResponseHistoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.service.SceneHistoryAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class RoomService {

    private final AiRequestService aiService;
    private final SceneHistoryAppService historyService;
    private final RunningStoryAppService runningService;
    private final ScenarioAppService scenarioService;
    private final AvatarAppService avatarAppService;
    private final UserAppService userAppService;
    private final UserScenarioSettingAppService userSettingService;
    private final ScenarioAvatarAppService scenarioAvatarService;
    private final PlayMapper mapper;
    private final AdventureLogAppService adventureLogService;

    private final RoomMessageService msgService;
    private final DialogArchiveAppService dialogArchiveAppService;


    public void startRoom(RequestPlayDTO.roomStartDTO roomStartDTO) {
        ScenarioDTO scenarioDTO = scenarioService.getScenario(roomStartDTO.scenarioId());


        List<PlayerDTO> playerList = new ArrayList<>();
        List<NpcDTO> npcList = new ArrayList<>();
        List<String> genre = trimGenre(scenarioDTO.genre());
        List<String> worldParts = new ArrayList<>();

        for (Long userCode : roomStartDTO.userCodes()){
            playerList.add(getPlayer(userCode, roomStartDTO.scenarioId()));
        }

        for (ScenarioAvatarDTO npcDto : scenarioDTO.scenarioAvatarList()){
            npcList.add(getNpc(npcDto));
        }

        for (WorldPartDTO worldDto : scenarioDTO.worldParts()){
            worldParts.add(worldDto.getPartName());
        }

        RunningStoryDTO runningDTO = RunningStoryDTO.builder()
                .roomNum(roomStartDTO.roomNum())
                .roomTitle(roomStartDTO.roomTitle())
                .scenarioTitle(scenarioDTO.scenarioTitle())
                .mainQuest(scenarioDTO.mainQuest())
                .subQuest(scenarioDTO.subQuest())
                .worldParts(worldParts)
                .detail(scenarioDTO.detail())
                .playerList(playerList)
                .npcList(npcList)
                .genre(genre)
                .build();

        aiService.startPlay(runningDTO);
        runningService.createRunningStory(runningDTO);
//        emitStartMessage(roomStartDTO.roomNum(), scenarioDTO);
//    }
//
//    public void emitStartMessage(Integer roomNum, ScenarioDTO scenarioDTO) {
//        msgService.emitToRoom(roomNum, "환영합니다!");
//        msgService.emitToRoom(roomNum, "여러분은 " + scenarioDTO.scenarioTitle() + "의 세계에 도착하셨습니다!");
//        msgService.emitToRoom(roomNum, "이 세계에서 여러분은 다음의 목표를 이루어야 합니다.");
//        msgService.emitToRoom(roomNum, scenarioDTO.mainQuest());
//        if (!scenarioDTO.subQuest().isEmpty()){
//            msgService.emitToRoom(roomNum, "여유가 있다면 다음의 목표도 달성해보세요!");
//            scenarioDTO.subQuest().forEach(s -> msgService.emitToRoom(roomNum, s));
//        }
    }

    public ResponsePlayDTO.RoomOpeningDTO getOpeningMessage(RequestPlayDTO.RoomNumDTO roomNumDTO) {
        return aiService.createGreeting(roomNumDTO).block();
    }

    public PlayerDTO getPlayer(Long userCode, Long scenarioCode) {
        PlayerDTO playerDTO = new PlayerDTO();
        UserResponseDTO.UserDetailDTO detailDTO = userAppService.findUser(userCode);
        UserAvatarDTO avatarDTO = avatarAppService.getUserAvatar(userCode);
        UserScenarioSettingDTO settingDTO = userSettingService.findSetting(
                new RequestSettingDTO.findOneDTO(userCode,scenarioCode)
        );

        mapper.updatePlayerFromDetail(playerDTO, detailDTO);
        mapper.updatePlayerFromAvatar(playerDTO,avatarDTO);
        mapper.updatePlayerFromSetting(playerDTO,settingDTO);

        playerDTO.setHealthPoints(playerDTO.getHealthPoints());
        playerDTO.setStatus(new ArrayList<>());
        return playerDTO;
    }

    public NpcDTO getNpc(ScenarioAvatarDTO avatarDTO) {
        NpcDTO npcDTO = new NpcDTO();

        mapper.updateNpcFromAvatarDTO(npcDTO, avatarDTO);

        npcDTO.setHealthPoints(avatarDTO.health());
        npcDTO.setStatus(new ArrayList<>());
        return npcDTO;
    }

    public List<String> trimGenre(List<GenreDTO> genre){
        List<String> genreList = new ArrayList<>();
        for (GenreDTO genreDTO : genre){
            genreList.add(genreDTO.genreName());
        }
        return genreList;
    }


    public List<ResponsePlayDTO.RunningListDTO> getRunningList() {
        List<RunningStoryDTO> runningList =runningService.getAllRunningStory();
        List<ResponsePlayDTO.RunningListDTO> runningDTOList = new ArrayList<>();
        for (RunningStoryDTO runningDTO : runningList){
            runningDTOList.add(new ResponsePlayDTO.RunningListDTO(
                    runningDTO.getRoomNum(),
                    runningDTO.getScenarioTitle()
            ));
        }

        return runningDTOList;
    }

    public void endRoom(Integer roomNum) {
        RunningStoryDTO runningStoryDTO = runningService.getRunningStoryById(roomNum);
        ResponseHistoryDTO.HistoryListDTO historyListDTO = historyService.getSceneHistory(roomNum);
        adventureLogService.saveArchive(runningStoryDTO, historyListDTO);
        runningService.deleteRunningStory(roomNum);
        aiService.endScenario(new RequestPlayDTO.RoomNumDTO(roomNum)).block();
    }

    public void trimRoomDataAll(){
        List<Integer> roomNumList = historyService.getRoomList().roomNumList();
        Integer[] uniqueRoomNumList = Arrays.stream(roomNumList.toArray())
                .distinct()
                .toArray(Integer[]::new);
        log.info("history exist : {}", (Object) uniqueRoomNumList);

        roomNumList.clear();
        for (Integer roomNum : uniqueRoomNumList){
            if(!adventureLogService.isLogExist(roomNum)){
                roomNumList.add(roomNum);
            }
        }

        log.info("unsaved history : {}", roomNumList);
        roomNumList.forEach(this::trimRoomData);

    }

    public void trimRoomData(Integer roomNum) {
//        RunningStoryDTO runningStoryDTO = runningService.getRunningStoryById(roomNum);
        ResponseHistoryDTO.HistoryListDTO historyListDTO = historyService.getSceneHistory(roomNum);
        adventureLogService.saveArchive(null, historyListDTO);

    }


}