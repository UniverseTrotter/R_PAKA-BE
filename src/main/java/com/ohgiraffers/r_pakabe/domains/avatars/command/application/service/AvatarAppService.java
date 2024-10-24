package com.ohgiraffers.r_pakabe.domains.avatars.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.model.Avatar;
import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.service.AvatarDomainService;
import com.ohgiraffers.r_pakabe.domains.user.command.domain.service.UserDomainService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        return UserAvatarDTO.fromEntity(avatar);
    }

    public void makeUserAvatar(UserAvatarDTO avatarDTO) {
        userDomainService.checkUserExistsByCode(avatarDTO.userCode());
        Avatar existAvatar = avatarDomainService.getAvatarByUserCode(avatarDTO.userCode());
        if (existAvatar != null) {
            throw new ApplicationException(ErrorCode.AVATAR_ALREADY_EXIST);
        }

        avatarDomainService.saveAvatar(new Avatar(avatarDTO));
    }

    public void updateUserAvatar(UserAvatarDTO avatarDTO) {
        userDomainService.checkUserExistsByCode(avatarDTO.userCode());
        Avatar existAvatar = avatarDomainService.getAvatarByUserCode(avatarDTO.userCode());
        if (existAvatar == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }
        Avatar newAvatar = new Avatar(
                existAvatar.getId(),
                avatarDTO.userCode(),
                avatarDTO.userAvatarGender(),
                avatarDTO.userAvatarHair(),
                avatarDTO.userAvatarBody(),
                avatarDTO.userAvatarSkin(),
                avatarDTO.userAvatarHand()
        );

        avatarDomainService.updateAvatar(newAvatar);
    }

    public List<UserAvatarDTO> getAllList() {
        List<Avatar> avatarList = avatarDomainService.getAllAvatars();
        if (avatarList == null || avatarList.isEmpty()) {
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }
        List<UserAvatarDTO> userAvatarDTOList = new ArrayList<>();
        for (Avatar avatar : avatarList) {
            userAvatarDTOList.add(UserAvatarDTO.fromEntity(avatar));
        }
        return userAvatarDTOList;
    }

    public void deleteAvatar(Long userCode) {
        userDomainService.checkUserExistsByCode(userCode);
        Avatar avatar = avatarDomainService.getAvatarByUserCode(userCode);
        if (avatar == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }
        avatarDomainService.deleteAvatar(avatar);
    }
}
