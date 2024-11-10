package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.service;

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
        return mapper.documentToDto(domainService.getRunningStory(roomNumber));
    }

    @Transactional
    public RunningStoryDTO createRunningStory(RunningStoryDTO dto) {
        RunningStory story = mapper.dtoToDocument(dto);
        story = domainService.createRunningStory(story);
        return mapper.documentToDto(story);
    }

    @Transactional
    public void deleteRunningStory(Integer roomNumber) {
        domainService.deleteRunningStory(roomNumber);
    }

}
