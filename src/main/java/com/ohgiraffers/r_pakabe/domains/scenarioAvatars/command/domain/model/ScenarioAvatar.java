package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
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
    @ColumnDefault("여기에 이름을 입력")
    private String avatarName;

    @Column
    @ColumnDefault("-1")
    private int outfit;


    @Override
    public String toString() {
        return "ScenarioAvatar{" +
                "senarioAvatarId=" + senarioAvatarId +
                ", avatarName='" + avatarName + '\'' +
                ", outfit=" + outfit +
                '}';
    }
}
