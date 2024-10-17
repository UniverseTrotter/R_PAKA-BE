package com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto;

public class UserResponseDTO {

    /**
     *  유저 아바타 정보
     * */
    public record userAvatarDTO(
            int userAvatarGender,
            int userAvatarHair,
            int userAvatarBody,
            int userAvatarSkin,
            int userAvatarHand
    ){}


}
