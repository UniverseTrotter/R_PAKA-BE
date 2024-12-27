package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.service;

import com.ohgiraffers.r_pakabe.common.PolyTime;
import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.AdventureLogDTO;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.AdventureLogMapper;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.ResponseAdventureDTO;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.model.AdventureLog;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.service.AdventureDomainService;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto.RoomArchiveDTO;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.HistoryDto;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.ResponseHistoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        AdventureLog adventureLog = domainService.findById(id);
        if (adventureLog == null){
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO_LOG);
        }
        return mapper.toDTO(adventureLog);
    }

    @Transactional(readOnly = true)
    public AdventureLogDTO findByRoomNum(Integer roomNum) throws ApplicationException {
        AdventureLog adventureLog = domainService.findByRoomNum(roomNum);
        if (adventureLog == null){
            throw new ApplicationException(ErrorCode.NO_SUCH_SCENARIO_LOG);
        }
        return mapper.toDTO(adventureLog);
    }

    @Transactional(readOnly = true)
    public boolean isLogExist(Integer roomNum){
        AdventureLog adventureLog = domainService.findByRoomNum(roomNum);
        return adventureLog != null;
    }



    @Transactional
    public void saveArchive(RunningStoryDTO runningStoryDTO, ResponseHistoryDTO.HistoryListDTO historyListDTO) {


        AdventureLogDTO adventureLogDTO = new AdventureLogDTO();
        if (runningStoryDTO != null){
            //기본 틀 입력
            adventureLogDTO = mapper.ToLogDTO(runningStoryDTO);
            //npc, player 이름 추출
            List<String> npcList = new ArrayList<>();
            List<String> playerList = new ArrayList<>();
            runningStoryDTO.getPlayerList().forEach(player -> playerList.add(player.getNickname()));
            runningStoryDTO.getNpcList().forEach(npc -> npcList.add(npc.getAvatarName()));
            adventureLogDTO.setPlayerList(playerList);
            adventureLogDTO.setNpcList(npcList);
        }else {
            adventureLogDTO.setRoomNum(historyListDTO.roomNum());
            adventureLogDTO.setCreatedAt(
                    historyListDTO.historyList().getFirst().createdAt()    //(최근 항목이 먼저)
            );
        }

        //히스토리 입력
        List<String> historyList = new ArrayList<>();
        historyListDTO.historyList().forEach(historyDto ->
                historyList.add(historyDto.history())
        );
        adventureLogDTO.setHistory(historyList);

        log.info("모험담 저장 : {}", adventureLogDTO);

        domainService.save(mapper.toEntity(adventureLogDTO));
    }
}

