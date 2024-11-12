package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto;

import com.ohgiraffers.r_pakabe.common.PolyTime;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarMapper;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model.RunningStory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RunningStoryMapper {
    ScenarioAvatarMapper INSTANCE = Mappers.getMapper(ScenarioAvatarMapper.class);

    RunningStory dtoToDocument (RunningStoryDTO dto);

    @Mapping(target = "startAt", expression = "java(convertCreatedAtToString(entity.getStartAt()))")
    @Mapping(target = "endAt", expression = "java(convertCreatedAtToString(entity.getEndAt()))")
    RunningStoryDTO documentToDto (RunningStory entity);


    RunningStory entityDtoToDocument(RunningEntityDTO entityDTO);

    void updateEntityDto(@MappingTarget RunningEntityDTO entityDTO, RunningStoryDTO updateDto);

    @Mapping(target = "startAt", expression = "java(convertCreatedAtToString(entity.getStartAt()))")
    @Mapping(target = "endAt", expression = "java(convertCreatedAtToString(entity.getEndAt()))")
    RunningEntityDTO documentToEntityDto(RunningStory entity);


    default String convertCreatedAtToString(LocalDateTime entityTime) {
        return entityTime == null ? null : PolyTime.PolyTimeConverter.convToStandardTime(entityTime); // PolyTime의 변환 메서드 호출
    }
}
