package com.ohgiraffers.r_pakabe.domains.user.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.user.command.domain.model.User;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.repository.UserRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {
    private final UserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User findByUserCode(Long code){
        return userRepository.findById(code).orElse(null);
    }

    @Nullable
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Nullable
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname).orElse(null);
    }
}
