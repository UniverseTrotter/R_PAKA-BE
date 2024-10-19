package com.ohgiraffers.r_pakabe.domains.user.command.domain.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.model.User;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.repository.UserRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDomainService {
    private final UserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findByUserCode(Long code){
        User user = userRepository.findById(code).orElse(null);
        if (user == null) {
            throw new ApplicationException(ErrorCode.USER_NOT_EXIST);
        }
        return user;
    }

    public void checkUserExistsByCode(Long code) throws ApplicationException {
        User user = userRepository.findById(code).orElse(null);
        if (user == null) {
            throw new ApplicationException(ErrorCode.USER_NOT_EXIST);
        }
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
