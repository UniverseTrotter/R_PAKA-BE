package com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.service;

import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model.RunningStory;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.repository.RunningStoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RunningStoryDomainService {

    private final RunningStoryRepository runningStoryRepository;

    public List<RunningStory> findAll() {
        return runningStoryRepository.findAll();
    }

    public RunningStory getRunningStory(Integer roomNum) {
        return runningStoryRepository.getRunningStoryByRoomNum(roomNum).orElse(null);
    }

    public RunningStory createRunningStory(RunningStory runningStory) {
        return runningStoryRepository.save(runningStory);
    }

    public RunningStory updateRunningStory(RunningStory runningStory) {
        return runningStoryRepository.save(runningStory);
    }

    public void deleteRunningStory(Integer roomNum) {
        runningStoryRepository.deleteRunningStoryByRoomNum(roomNum);
    }


}
