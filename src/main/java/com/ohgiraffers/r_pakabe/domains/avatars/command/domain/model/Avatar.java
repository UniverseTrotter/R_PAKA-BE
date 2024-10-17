package com.ohgiraffers.r_pakabe.domains.avatars.command.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int userAvatarGender;
    @Column
    private int userAvatarHair;
    @Column
    private int userAvatarBody;
    @Column
    private int userAvatarSkin;
    @Column
    private int userAvatarHand;

    @Builder
    public Avatar(Long userCode, int userAvatarGender, int userAvatarHair, int userAvatarBody, int userAvatarSkin, int userAvatarHand) {
        this.userCode = userCode;
        this.userAvatarGender = userAvatarGender;
        this.userAvatarHair = userAvatarHair;
        this.userAvatarBody = userAvatarBody;
        this.userAvatarSkin = userAvatarSkin;
        this.userAvatarHand = userAvatarHand;
    }


    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", userCode=" + userCode +
                ", userAvatarGender=" + userAvatarGender +
                ", userAvatarHair=" + userAvatarHair +
                ", userAvatarBody=" + userAvatarBody +
                ", userAvatarSkin=" + userAvatarSkin +
                ", userAvatarHand=" + userAvatarHand +
                '}';
    }
}
