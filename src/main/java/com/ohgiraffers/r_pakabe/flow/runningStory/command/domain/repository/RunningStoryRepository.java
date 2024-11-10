package com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.repository;

import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model.RunningStory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RunningStoryRepository extends MongoRepository<RunningStory, String> {
    Optional<RunningStory> getRunningStoryByRoomNum(Integer roomNum);

    void deleteRunningStoryByRoomNum(Integer roomNum);
}
