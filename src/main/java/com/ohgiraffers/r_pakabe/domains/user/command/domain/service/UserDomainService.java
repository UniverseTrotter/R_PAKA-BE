package com.ohgiraffers.r_pakabe.domains.user.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.user.command.domain.model.User;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {
    private final UserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserCode(Long code){
        return userRepository.findById(code).orElse(null);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }
}
