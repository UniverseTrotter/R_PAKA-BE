package com.ohgiraffers.r_pakabe.flow.dialogArchive.command.domain.service;

import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.domain.model.DialogArchive;
import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.domain.repository.DialogArchiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DialogArchiveDomainService {
    private final DialogArchiveRepository repository;

    public List<DialogArchive> findAllArchive(){
        return repository.findAll();
    }

    public List<DialogArchive> findArchiveByRoomNum(Integer roomNum){
        return repository.findByRoomNumOrderByCreatedAtDesc(roomNum);
    }

    public DialogArchive findLatestByRoomNum(Integer roomNum){
        return repository.findFirstByRoomNumOrderByCreatedAtDesc(roomNum).orElse(null);
    }

    public DialogArchive create(DialogArchive dialogArchive){
        return repository.save(dialogArchive);
    }

    public void deleteByRoomNum(Integer roomNum){
        repository.deleteByRoomNum(roomNum);
    }
}
