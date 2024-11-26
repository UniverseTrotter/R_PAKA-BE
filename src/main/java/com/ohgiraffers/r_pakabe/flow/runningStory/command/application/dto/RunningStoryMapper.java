package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto;

import com.ohgiraffers.r_pakabe.common.PolyTime;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model.RunningStory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RunningStoryMapper {
    RunningStoryMapper INSTANCE = Mappers.getMapper(RunningStoryMapper.class);

    RunningStory dtoToDocument (RunningStoryDTO dto);

    @Mapping(target = "startAt", expression = "java(convertCreatedAtToString(entity.getStartAt()))")
    @Mapping(target = "endAt", expression = "java(convertCreatedAtToString(entity.getEndAt()))")
    RunningStoryDTO documentToDto (RunningStory entity);


    @Mapping(target = "startAt", expression = "java(convertCreatedAtToLocalDateTime(entityDTO.getStartAt()))")
    @Mapping(target = "endAt", expression = "java(convertCreatedAtToLocalDateTime(entityDTO.getEndAt()))")
    RunningStory entityDtoToDocument(RunningEntityDTO entityDTO);

    // 시작 시간은 자동생성이라 업데이트 되면 안됨
    @Mapping(source = "updateDto", target = "startAt", ignore = true)
    void updateEntityDto(@MappingTarget RunningEntityDTO entityDTO, RunningStoryDTO updateDto);

    @Mapping(target = "startAt", expression = "java(convertCreatedAtToString(entity.getStartAt()))")
    @Mapping(target = "endAt", expression = "java(convertCreatedAtToString(entity.getEndAt()))")
    RunningEntityDTO documentToEntityDto(RunningStory entity);

    default LocalDateTime convertCreatedAtToLocalDateTime(String createdAt) {
        return PolyTime.PolyTimeConverter.convertFromString(createdAt);
    }

    default String convertCreatedAtToString(LocalDateTime entityTime) {
        return PolyTime.PolyTimeConverter.convToStandardTime(entityTime); // PolyTime의 변환 메서드 호출
    }
}
