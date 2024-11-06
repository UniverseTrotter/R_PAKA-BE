package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
//    @ColumnDefault(value = "여기에 이름을 입력")
    private String avatarName;

    @Column
    @ColumnDefault("-1")
    private Integer outfit;

    @Column
    @ColumnDefault("false")
    private Boolean isPlayable;

    @Builder
    public ScenarioAvatar(String avatarName, Integer outfit, Boolean isPlayable) {
        this.avatarName = avatarName;
        this.outfit = outfit;
        this.isPlayable = isPlayable;
    }

    @Override
    public String toString() {
        return "ScenarioAvatar{" +
                "scenarioAvatarId=" + scenarioAvatarId +
                ", avatarName='" + avatarName + '\'' +
                ", outfit=" + outfit +
                ", isPlayable=" + isPlayable +
                '}';
    }
}
