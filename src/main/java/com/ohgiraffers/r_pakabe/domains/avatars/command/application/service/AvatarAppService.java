package com.ohgiraffers.r_pakabe.domains.avatars.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.model.Avatar;
import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.service.AvatarDomainService;
import org.springframework.stereotype.Service;

@Service
public class AvatarAppService {
    private final AvatarDomainService avatarDomainService;

    public AvatarAppService(AvatarDomainService avatarDomainService) {
        this.avatarDomainService = avatarDomainService;
    }

    public UserResponseDTO.userAvatarDTO getUserAvatar(Long userCode) {
        Avatar avatar = avatarDomainService.getAvatarByUserCode(userCode);
        if (avatar == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }

        return new UserResponseDTO.userAvatarDTO(
                avatar.getUserAvatarGender(),
                avatar.getUserAvatarHair(),
                avatar.getUserAvatarBody(),
                avatar.getUserAvatarSkin(),
                avatar.getUserAvatarHand()
        );
    }
}
