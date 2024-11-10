package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    //정보
    private Long userCode;
    private String nickname;

    //외형
    private Integer userAvatarGender;
    private Integer userAvatarHair;
    private Integer userAvatarBody;
    private Integer userAvatarSkin;
    private Integer userAvatarHand;

    //스탯
    private Integer health;
    private Integer strength;
    private Integer dex;

    //현재 상태
    private Integer healthPoints;
    private List<String> status;

}
