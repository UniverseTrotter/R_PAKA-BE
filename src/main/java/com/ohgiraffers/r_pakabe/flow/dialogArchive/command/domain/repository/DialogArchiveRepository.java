package com.ohgiraffers.r_pakabe.flow.dialogArchive.command.domain.repository;

import com.ohgiraffers.r_pakabe.flow.dialogArchive.command.domain.model.DialogArchive;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DialogArchiveRepository extends MongoRepository<DialogArchive, String> {

    List<DialogArchive> findByRoomNumOrderByCreatedAtDesc(Integer roomNum);

    Optional<DialogArchive> findFirstByRoomNumOrderByCreatedAtDesc(Integer roomNum);

    void deleteByRoomNum(Integer roomNum);

}
