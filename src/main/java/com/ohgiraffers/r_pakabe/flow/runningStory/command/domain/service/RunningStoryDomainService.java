package com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.service;

import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.PlayerDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model.RunningStory;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.repository.RunningStoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RunningStoryDomainService {

    private final RunningStoryRepository repository;

    public List<RunningStory> findAll() {
        return repository.findAll();
    }

    public RunningStory getRunningStory(Integer roomNum) {
        return repository.getRunningStoryByRoomNum(roomNum).orElse(null);
    }

    public RunningStory createRunningStory(RunningStory runningStory) {
        return repository.save(runningStory);
    }

    public RunningStory updateRunningStory(RunningStory runningStory) {
        return repository.save(runningStory);
    }

    public void deleteRunningStory(Integer roomNum) {
        repository.deleteRunningStoryByRoomNum(roomNum);
    }


    public PlayerDTO findPlayerDTOByRoomNumAndUserCode(Integer roomNum, Long userCode) {
        return repository.findPlayerDTOByRoomNumAndUserCode(roomNum, userCode).orElse(null);
    }

}
