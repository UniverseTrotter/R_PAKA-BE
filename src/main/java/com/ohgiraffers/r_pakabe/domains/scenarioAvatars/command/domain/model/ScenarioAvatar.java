package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.RequestAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@ToString
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table
public class ScenarioAvatar {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scenarioAvatarId;

    @Column
    private String avatarName;

    @Column
    @ColumnDefault("-1")
    private Integer outfit;

    @Column
    @ColumnDefault("false")
    private Boolean isPlayable;

    @Column
    private Integer health;

    @Column
    private Integer strength;

    @Column
    private Integer dex;


    @Column
    private Float axisX;

    @Column
    private Float axisY;

    @Column
    private Float axisZ;

    @Column
    private Integer worldId;


}
