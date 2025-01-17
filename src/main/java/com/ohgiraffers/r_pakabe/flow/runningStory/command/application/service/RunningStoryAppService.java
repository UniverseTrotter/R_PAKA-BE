package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.PlayerDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningEntityDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryMapper;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model.RunningStory;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.service.RunningStoryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class RunningStoryAppService {

    private final RunningStoryDomainService domainService;
    private final RunningStoryMapper mapper;

    private RunningStory getIfExist(Integer roomNum){
        RunningStory story = domainService.getRunningStory(roomNum);
        if (story == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_ROOM);
        }
        return story;
    }


    @Transactional(readOnly = true)
    public List<RunningStoryDTO> getAllRunningStory() {
        List<RunningStoryDTO> dtos = new ArrayList<>();
        List<RunningStory> storyList= domainService.findAll();
        for (RunningStory story : storyList) {
            dtos.add(mapper.documentToDto(story));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public RunningStoryDTO getRunningStoryById(Integer roomNumber) {
        RunningStory story = getIfExist(roomNumber);
        return mapper.documentToDto(story);
    }

    @Transactional(readOnly = true)
    public PlayerDTO getPlayerDTO(Integer roomNumber, Long userCode) {
        RunningStory story = getIfExist(roomNumber);
        List<PlayerDTO> playerList = story.getPlayerList();
        PlayerDTO playerDTO = new PlayerDTO();
        for (PlayerDTO player : playerList) {
            if (player.getUserCode().equals(userCode)) {
                playerDTO = player;
                break;
            }
        }
        return playerDTO;
    }

    @Transactional
    public RunningStoryDTO createRunningStory(RunningStoryDTO dto) {
        RunningStory story = domainService.getRunningStory(dto.getRoomNum());
        if (story != null) {
            throw new ApplicationException(ErrorCode.REDUNDANT_ROOM_NUMBER);
        }
        story = domainService.createRunningStory(mapper.dtoToDocument(dto));
        return mapper.documentToDto(story);
    }

    @Transactional
    public RunningStoryDTO updateRunningStory(RunningStoryDTO updateDto) {
        RunningStory story = getIfExist(updateDto.getRoomNum());
        RunningEntityDTO entityDTO = mapper.documentToEntityDto(story);
        mapper.updateEntityDto(entityDTO, updateDto);
        story = domainService.updateRunningStory(mapper.entityDtoToDocument(entityDTO));
        return mapper.documentToDto(story);
    }

    @Transactional
    public void deleteRunningStory(Integer roomNumber) {
        getIfExist(roomNumber);
        domainService.deleteRunningStory(roomNumber);
    }

}
