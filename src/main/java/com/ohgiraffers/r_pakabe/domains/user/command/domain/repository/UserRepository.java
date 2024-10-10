package com.ohgiraffers.r_pakabe.domains.user.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.user.command.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
