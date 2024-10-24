package com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.model.Avatar;


public record UserAvatarDTO(
        Long userCode,
        int userAvatarGender,
        int userAvatarHair,
        int userAvatarBody,
        int userAvatarSkin,
        int userAvatarHand
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
