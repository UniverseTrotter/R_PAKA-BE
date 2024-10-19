package com.ohgiraffers.r_pakabe.domains.avatars.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserRequestDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserResponseDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.model.Avatar;
import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.service.AvatarDomainService;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.service.UserDomainService;
import org.springframework.stereotype.Service;

@Service
public class AvatarAppService {
    private final AvatarDomainService avatarDomainService;
    private final UserDomainService userDomainService;

    public AvatarAppService(AvatarDomainService avatarDomainService, UserDomainService userDomainService) {
        this.avatarDomainService = avatarDomainService;
        this.userDomainService = userDomainService;
    }

    public UserAvatarDTO getUserAvatar(Long userCode) {
        userDomainService.checkUserExistsByCode(userCode);
        Avatar avatar = avatarDomainService.getAvatarByUserCode(userCode);
        if (avatar == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }

        return new UserAvatarDTO(avatar);
    }

    public void makeUserAvatar(UserAvatarDTO avatarDTO) {
        userDomainService.checkUserExistsByCode(avatarDTO.userCode());
        avatarDomainService.updateAvatar(new Avatar(avatarDTO));
    }
}
