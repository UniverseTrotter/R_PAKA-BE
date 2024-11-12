package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.service;


import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.model.SceneHistory;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.repository.SceneHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SceneHistoryDomainService {
    private final SceneHistoryRepository repository;

    public List<SceneHistory> findAll() {
        return repository.findAll();
    }

    public List<SceneHistory> findByRoomNum(Integer roomNum) {
        // Sort by createdAt descending (최근 항목이 먼저)
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return repository.findByRoomNum(roomNum, sort);
    }

    public SceneHistory create(SceneHistory entity) {
        return repository.save(entity);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public void deleteByRoomNum(Integer roomNum) {
        repository.deleteByRoomNum(roomNum);
    }

}
