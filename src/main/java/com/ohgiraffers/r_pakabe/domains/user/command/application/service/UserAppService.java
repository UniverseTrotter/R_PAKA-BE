package com.ohgiraffers.r_pakabe.domains.user.command.application.service;

import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserRequestDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.model.User;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.service.UserDomainService;
import org.springframework.stereotype.Service;

@Service
public class UserAppService {

    private final UserDomainService userDomainService;

    public UserAppService(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    public UserResponseDTO.authDTO userLogin(UserRequestDTO.LoginDTO loginDTO) {
        User user = userDomainService.findByUserId(loginDTO.userId());

        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(loginDTO.password())) {
            return new UserResponseDTO.authDTO(user.getUserCode());
        }else {
            throw new RuntimeException();
        }
    }
}
