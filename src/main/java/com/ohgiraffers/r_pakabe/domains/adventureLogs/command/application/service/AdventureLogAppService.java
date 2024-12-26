package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.AdventureLogDTO;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.AdventureLogMapper;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.ResponseAdventureDTO;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.service.AdventureDomainService;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto.RoomArchiveDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.HistoryDto;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.ResponseHistoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdventureLogAppService {

    private final AdventureDomainService domainService;
    private final AdventureLogMapper mapper;

    @Transactional(readOnly = true) //readOnly 인 경우, jpql 이 더 빠를 수도
    public ResponseAdventureDTO.LogList getAllList(){
        List<ResponseAdventureDTO.ShortenLog> adventures = new ArrayList<>();
        List<AdventureLogDTO> adventureLogs = mapper.toDTO(domainService.findAll());
        adventureLogs.forEach(adventureLog -> adventures.add(mapper.shortenLog(adventureLog)));

        return new ResponseAdventureDTO.LogList(adventures);
    }

    @Transactional(readOnly = true)
    public AdventureLogDTO findById(Long id) {
        AdventureLogDTO adventureLogDTO = mapper.toDTO(domainService.findById(id));
        if (adventureLogDTO == null){
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO_LOG);
        }
        return adventureLogDTO;
    }


    public void saveArchive(RunningStoryDTO runningStoryDTO, ResponseHistoryDTO.HistoryListDTO historyListDTO, RoomArchiveDTO archiveDTO) {
        List<HistoryDto> historyList = historyListDTO.historyList();


    }
}

