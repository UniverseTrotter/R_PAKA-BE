package com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table
public class ScenarioTag {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagCode;

    //장르 이름
    @Column(length = 100, unique = true)
    private String tagName;

    @Override
    public String toString() {
        return "Tag{" +
                "tagCode=" + tagCode +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
