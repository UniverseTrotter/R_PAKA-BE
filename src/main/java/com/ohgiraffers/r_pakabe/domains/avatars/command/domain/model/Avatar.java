package com.ohgiraffers.r_pakabe.domains.avatars.command.domain.model;

import com.ohgiraffers.r_pakabe.domains.avatars.command.application.dto.UserAvatarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저의 외형을 저장
 * */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "TBL_AVATAR")
public class Avatar {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userCode;

    @Column
    private Integer userAvatarGender;
    @Column
    private Integer userAvatarHair;
    @Column
    private Integer userAvatarBody;
    @Column
    private Integer userAvatarSkin;
    @Column
    private Integer userAvatarHand;

    @Builder
    public Avatar(Long userCode, Integer userAvatarGender, Integer userAvatarHair, Integer userAvatarBody, Integer userAvatarSkin, Integer userAvatarHand) {
        this.userCode = userCode;
        this.userAvatarGender = userAvatarGender;
        this.userAvatarHair = userAvatarHair;
        this.userAvatarBody = userAvatarBody;
        this.userAvatarSkin = userAvatarSkin;
        this.userAvatarHand = userAvatarHand;
    }

    public Avatar(UserAvatarDTO avatarDTO) {
        this.userCode = avatarDTO.userCode();
        this.userAvatarGender = avatarDTO.userAvatarGender();
        this.userAvatarHair = avatarDTO.userAvatarHair();
        this.userAvatarBody = avatarDTO.userAvatarBody();
        this.userAvatarSkin = avatarDTO.userAvatarSkin();
        this.userAvatarHand = avatarDTO.userAvatarHand();
    }


    @Override
    public String toString() {
        return "Avatar{" +
                "avatarId=" + id +
                ", userCode=" + userCode +
                ", userAvatarGender=" + userAvatarGender +
                ", userAvatarHair=" + userAvatarHair +
                ", userAvatarBody=" + userAvatarBody +
                ", userAvatarSkin=" + userAvatarSkin +
                ", userAvatarHand=" + userAvatarHand +
                '}';
    }
}
