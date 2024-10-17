package com.ohgiraffers.r_pakabe.domains.avatars.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByUserCode(Long userCode);

}
