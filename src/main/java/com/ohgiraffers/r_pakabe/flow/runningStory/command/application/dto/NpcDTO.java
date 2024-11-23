package com.ohgiraffers.r_pakabe.flow.runningStory.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NpcDTO {

    //정보
    private Integer scenarioAvatarId;
    private String avatarName;
    private String avatarDetail;

    //외형
    private Integer outfit;

    //스탯
    private Integer health;
    private Integer strength;
    private Integer dex;

    //현재 상태
    private Integer healthPoints;
    private List<String> status;
}
