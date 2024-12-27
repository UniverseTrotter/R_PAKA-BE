package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto;

import com.ohgiraffers.r_pakabe.common.PolyTime;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.domain.model.SceneHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoryMapper {

    HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);

    @Mapping(target = "createdAt", expression = "java(convertCreatedAtToString(entity.getCreatedAt()))")
    SceneHistoryDTO toDto(SceneHistory entity);
//    SceneHistory toEntity(SceneHistoryDTO dto);

    @Mapping(target = "createdAt", expression = "java(convertCreatedAtToString(entity.getCreatedAt()))")
    HistoryDto entityToHistoryDto(SceneHistory entity);

    SceneHistory createdToToEntity(RequestHistoryDTO.createDTO createDTO);


    default String convertCreatedAtToString(LocalDateTime createdAt) {
        return createdAt == null ? null : PolyTime.PolyTimeConverter.convToStandardTime(createdAt); // PolyTime의 변환 메서드 호출
    }
}
