package com.ohgiraffers.r_pakabe.domains.user.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.user.command.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {
    private UserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
