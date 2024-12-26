package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.model.AdventureLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdventureLogMapper {
    AdventureLogDTO toDTO(AdventureLog adventureLog);
    AdventureLog toEntity(AdventureLogDTO adventureLogDTO);
    ResponseAdventureDTO.ShortenLog shortenLog(AdventureLogDTO adventureLogDTO);

    List<AdventureLogDTO> toDTO(List<AdventureLog> adventureLogs);

}
