package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto;


import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model.WorldPart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorldPartMapper {
    WorldPartMapper INSTANCE = Mappers.getMapper(WorldPartMapper.class);

    WorldPartDTO toDTO(WorldPart worldPart);
    WorldPart toEntity(WorldPartDTO worldPartDTO);


//    @Mapping(target = "partName", source = "WorldName")
//    WorldPart createDtoToEntity(RequestWorldDTO.CreateWorldDTO createWorldDTO);

    @Mapping(target = "partName", source = "WorldName")
    void updateWorldDto(@MappingTarget WorldPartDTO worldPartDTO, RequestWorldDTO.UpdateWorldDTO updateWorldDTO);

    @Mapping(target = "partName", source = "WorldName")
    WorldPartDTO createDtoToDto(RequestWorldDTO.CreateWorldDTO createWorldDTO);
}
