package com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.repository;

import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.PlayerDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model.RunningStory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RunningStoryRepository extends MongoRepository<RunningStory, String> {
    Optional<RunningStory> getRunningStoryByRoomNum(Integer roomNum);

    void deleteRunningStoryByRoomNum(Integer roomNum);

    @Query(value = "{ 'roomNum': ?0, 'playerList.userCode': ?1 }", fields = "{ 'playerList.$': 1 }")
    Optional<PlayerDTO> findPlayerDTOByRoomNumAndUserCode(Integer roomNum, Long userCode);

}
