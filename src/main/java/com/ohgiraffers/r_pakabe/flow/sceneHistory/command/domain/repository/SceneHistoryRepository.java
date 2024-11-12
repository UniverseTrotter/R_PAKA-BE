package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.repository;

import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.model.SceneHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SceneHistoryRepository extends MongoRepository<SceneHistory, String> {

    List<SceneHistory> findByRoomNum(Integer roomNum, Sort sort);

    void deleteByRoomNum(Integer roomNum);
}
