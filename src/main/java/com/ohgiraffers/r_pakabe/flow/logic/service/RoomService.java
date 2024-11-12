package com.ohgiraffers.r_pakabe.flow.logic.service;


import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.service.AvatarAppService;
import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.genres.command.application.service.GenreAppService;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.service.ScenarioAvatarAppService;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.ScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.service.ScenarioAppService;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.service.UserAppService;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.RequestSettingDTO;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.UserScenarioSettingDTO;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.service.UserScenarioSettingAppService;
import com.ohgiraffers.r_pakabe.flow.aiComm.service.AiRequestService;
import com.ohgiraffers.r_pakabe.flow.logic.dto.PlayMapper;
import com.ohgiraffers.r_pakabe.flow.logic.dto.RequestPlayDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.NpcDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.PlayerDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.service.RunningStoryAppService;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.service.SceneHistoryAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public void startRoom(RequestPlayDTO.roomStartDTO roomStartDTO) {
        ScenarioDTO scenarioDTO = scenarioService.getScenario(roomStartDTO.scenarioId());


        List<PlayerDTO> playerList = new ArrayList<>();
        List<NpcDTO> npcList = new ArrayList<>();
        List<String> genre = trimGenre(scenarioDTO.genre());

        for (Long userCode : roomStartDTO.userCodes()){
            playerList.add(getPlayer(userCode, roomStartDTO.scenarioId()));
        }

        for (ScenarioAvatarDTO npcDto : scenarioDTO.scenarioAvatarList()){
            npcList.add(getNpc(npcDto));
        }

        RunningStoryDTO runningDTO = RunningStoryDTO.builder()
                .roomNum(roomStartDTO.roomNum())
                .scenarioTitle(scenarioDTO.scenarioTitle())
                .mainQuest(scenarioDTO.mainQuest())
                .subQuest(scenarioDTO.subQuest())
                .detail(scenarioDTO.detail())
                .playerList(playerList)
                .npcList(npcList)
                .genre(genre)
                .build();

        runningService.createRunningStory(runningDTO);

        aiService.startPlay(runningDTO);
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


    public void endRoom(Integer RoomNum) {
        runningService.deleteRunningStory(RoomNum);
    }

}