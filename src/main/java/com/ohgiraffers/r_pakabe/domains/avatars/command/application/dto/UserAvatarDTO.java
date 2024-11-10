package com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.model.Avatar;


public record UserAvatarDTO(
        Long userCode,
        Integer userAvatarGender,
        Integer userAvatarHair,
        Integer userAvatarBody,
        Integer userAvatarSkin,
        Integer userAvatarHand
){
    public static UserAvatarDTO fromEntity(Avatar avatar) {
        return new UserAvatarDTO(
                avatar.getUserCode(),
                avatar.getUserAvatarGender(),
                avatar.getUserAvatarHair(),
                avatar.getUserAvatarBody(),
                avatar.getUserAvatarSkin(),
                avatar.getUserAvatarHand()
        );
    }
}
