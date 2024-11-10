package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarMapper;
import com.ohgiraffers.r_pakabe.flow.runningStory.command.domain.model.RunningStory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RunningStoryMapper {
    ScenarioAvatarMapper INSTANCE = Mappers.getMapper(ScenarioAvatarMapper.class);

    RunningStory dtoToDocument (RunningStoryDTO dto);
    RunningStoryDTO documentToDto (RunningStory dto);


    RunningStory entityDtoToDocument(RunningEntityDTO entityDTO);

    void updateEntityDto(@MappingTarget RunningEntityDTO entityDTO, RunningStoryDTO updateDto);

    RunningEntityDTO documentToEntityDto(RunningStory story);
}
