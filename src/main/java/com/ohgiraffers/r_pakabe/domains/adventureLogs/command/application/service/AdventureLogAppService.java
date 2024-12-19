package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.service;

import com.ohgiraffers.r_pakabe.common.PolyTime;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.AdventureLogDTO;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.AdventureLogMapper;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto.ResponseAdventureLogDTO;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.model.AdventureLog;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.service.AdventureLogDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdventureLogAppService {

    private final AdventureLogDomainService domainService;
    private final AdventureLogMapper mapper;

    public List<AdventureLogDTO> getAll() {
        List<AdventureLog> adventureLogs= domainService.findAll();
        return mapper.toDTOs(adventureLogs);
    }

    public ResponseAdventureLogDTO.ListDTO getList() {
        List<AdventureLog> adventureLogs= domainService.findAll();
        List<ResponseAdventureLogDTO.briefDTO> result = new ArrayList<>();
        adventureLogs.forEach(adventureLog -> {
            result.add(
                    new ResponseAdventureLogDTO.briefDTO(
                            adventureLog.getRoomNum(),
                            adventureLog.getRoomTitle(),
                            adventureLog.getScenarioTitle(),
                            PolyTime.PolyTimeConverter.convToStandardTime(adventureLog.getCreatedAt())
                    )
            );
        });
        return new ResponseAdventureLogDTO.ListDTO(result);
    }


    public void saveArchive() {
    }

}

