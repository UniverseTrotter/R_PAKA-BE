package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.*;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.model.SceneHistory;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.service.SceneHistoryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class SceneHistoryAppService {

    private final SceneHistoryDomainService domainService;
    private final HistoryMapper mapper;

    @Transactional(readOnly = true)
    public ResponseHistoryDTO.HistoryListDTO getAllSceneHistory() {
        List<HistoryDto> historyList = new ArrayList<>();
        List<SceneHistory> entityList = domainService.findAll();
        for (SceneHistory entity : entityList) {
            historyList.add(mapper.entityToHistoryDto(entity));
        }
        return new ResponseHistoryDTO.HistoryListDTO(-1, historyList);
    }

    @Transactional(readOnly = true)
    public ResponseHistoryDTO.HistoryListDTO getSceneHistory(Integer roomNum) {
        List<SceneHistory> entityList = domainService.findByRoomNum(roomNum);
        List<HistoryDto> historyList = new ArrayList<>();
        for (SceneHistory entity : entityList) {
            historyList.add(mapper.entityToHistoryDto(entity));
        }
        return new ResponseHistoryDTO.HistoryListDTO(roomNum, historyList);
    }

    @Transactional
    public SceneHistoryDTO createHistory(RequestHistoryDTO.createDTO createDTO) {
        log.info("createHistory : {}", createDTO);
        return mapper.toDto(
                domainService.create(
                        mapper.createdToToEntity(createDTO)
                )
        );
    }

    @Transactional
    public void deleteHistory(Integer roomNumber) {
        domainService.deleteByRoomNum(roomNumber);
    }

    @Transactional(readOnly = true)
    public ResponseHistoryDTO.RoomListDTO getRoomList() {
        List<SceneHistory> entityList = domainService.findAll();
        List<Integer> roomNumList = new ArrayList<>();
        entityList.forEach(entity -> roomNumList.add(entity.getRoomNum()));
        return new ResponseHistoryDTO.RoomListDTO(roomNumList);
    }
}
