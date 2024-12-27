package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.application.dto;

import com.ohgiraffers.r_pakabe.common.PolyTime;
import com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.model.AdventureLog;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto.RunningStoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdventureLogMapper {

    @Mapping(target = "startAt", expression = "java(convertCreatedAtToString(entity.getStartAt()))")
    @Mapping(target = "endAt", expression = "java(convertCreatedAtToString(entity.getEndAt()))")
    @Mapping(target = "createdAt", expression = "java(convertCreatedAtToString(entity.getCreatedAt()))")
    AdventureLogDTO toDTO(AdventureLog entity);

    AdventureLog toEntity(AdventureLogDTO adventureLogDTO);
    ResponseAdventureDTO.ShortenLog shortenLog(AdventureLogDTO adventureLogDTO);

    List<AdventureLogDTO> toDTO(List<AdventureLog> adventureLogs);

    @Mapping(target = "playerList", ignore = true)
    @Mapping(target = "npcList", ignore = true)
    AdventureLogDTO ToLogDTO(RunningStoryDTO runningStoryDTO);

    default String convertCreatedAtToString(LocalDateTime createdAt) {
        return createdAt == null ? null : PolyTime.PolyTimeConverter.convToStandardTime(createdAt); // PolyTime의 변환 메서드 호출
    }
}
