package com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.application.dto.*;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.domain.model.DialogArchive;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.domain.service.DialogArchiveDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DialogArchiveAppService {
    private final DialogArchiveDomainService domainService;
    DialogArchiveMapper mapper;

    public List<DialogArchiveDTO> findAll() {
        List<DialogArchive> allArchive = domainService.findAllArchive();
        List<DialogArchiveDTO> ArchiveDTOs = new ArrayList<>();
        for (DialogArchive dialogArchive : allArchive) {
            ArchiveDTOs.add(mapper.toDto(dialogArchive));
        }
        return ArchiveDTOs;
    }

    public RoomArchiveDTO findRoomArchiveByRoomNum(Integer roomNum) {
        List<DialogArchive> roomArchive = domainService.findArchiveByRoomNum(roomNum);
        List<SingleLineDTO> dialogs = new ArrayList<>();
        for (DialogArchive dialogArchive : roomArchive) {
            dialogs.add(
                    new SingleLineDTO(dialogArchive.getSpeaker(),dialogArchive.getDialog())
            );
        }
        return new RoomArchiveDTO(roomNum,dialogs);
    }

    public DialogArchiveDTO findLatestByRoomNum(Integer roomNum) {
        DialogArchive latest = domainService.findLatestByRoomNum(roomNum);
        if (latest == null) {
            throw new ApplicationException(ErrorCode.NO_DIALOG);
        }
        return mapper.toDto(latest);
    }

    public DialogArchiveDTO save(CreateDialogArchiveDTO createDialogArchiveDTO) {
        DialogArchive created = DialogArchive.builder()
                .roomNum(createDialogArchiveDTO.roomNum())
                .speaker(createDialogArchiveDTO.speaker())
                .dialog(createDialogArchiveDTO.dialog())
                .build();
        created = domainService.create(created);
        return mapper.toDto(created);
    }

    public void delete(Integer roomNum) {
        DialogArchive latest = domainService.findLatestByRoomNum(roomNum);
        if (latest == null) {
            throw new ApplicationException(ErrorCode.NO_DIALOG);
        }
        domainService.deleteByRoomNum(roomNum);
    }
}
