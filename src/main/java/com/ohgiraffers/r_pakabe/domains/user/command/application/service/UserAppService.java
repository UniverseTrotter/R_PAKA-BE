package com.ohgiraffers.r_pakabe.domains.user.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserRequestDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.model.User;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.service.UserDomainService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserAppService {

    private final UserDomainService userDomainService;

    public UserAppService(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }


    @Transactional
    public UserResponseDTO.authDTO userLogin(UserRequestDTO.LoginDTO loginDTO) {
        User user = userDomainService.findByUserId(loginDTO.userId());

        if (user == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_USER);
        }

        if (user.getPassword().equals(loginDTO.password())) {
            return new UserResponseDTO.authDTO(user.getUserCode());
        }else {
            throw new ApplicationException(ErrorCode.INVALID_PASSWORD);
        }
    }
}
