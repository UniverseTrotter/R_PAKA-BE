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
    private Integer senarioAvatarId;

    @Column
//    @ColumnDefault(value = "여기에 이름을 입력")
    private String avatarName;

    @Column
    @ColumnDefault("-1")
    private int outfit;

    @Builder
    public ScenarioAvatar(String avatarName, int outfit) {
        this.avatarName = avatarName;
        this.outfit = outfit;
    }

    @Override
    public String toString() {
        return "ScenarioAvatar{" +
                "senarioAvatarId=" + senarioAvatarId +
                ", avatarName='" + avatarName + '\'' +
                ", outfit=" + outfit +
                '}';
    }
}
