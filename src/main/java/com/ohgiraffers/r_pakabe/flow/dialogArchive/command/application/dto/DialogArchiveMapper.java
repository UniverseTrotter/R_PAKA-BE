package com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto;

import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.domain.model.DialogArchive;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DialogArchiveMapper {
    DialogArchiveDTO toDto(DialogArchive dialogArchive);
    DialogArchive toEntity(DialogArchiveDTO dialogArchiveDTO);
}
