package com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model;

import com.ohgiraffers.r_pakabe.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table
public class Scenario extends BaseTimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scenarioCode;

    //시나리오 제목
    @Column(length = 250, unique = true)
    private String scenarioTitle;

    //장르 리스트
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "integer[]")
    private List<Integer> genre;

    //메인 퀘스트
    @Column(length = 1000)
    private String mainQuest;

    //서브 퀘스트
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "text[]")
    private List<String> subQuest;

    //세계관 설명
    @Column(length = 10000)
    private String detail;

    //아바타 리스트   //가져오기
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "integer[]")
    private List<Integer> scenarioAvatarList;

    //월드 파츠 리스트 //가져오기
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "integer[]")
    private List<Integer> worldParts;

    //태그 리스트
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "integer[]")
    private List<Integer> tags;


    @Override
    public String toString() {
        return "Scenario{" +
                "scenarioCode=" + scenarioCode +
                ", scenarioTitle='" + scenarioTitle + '\'' +
                ", genre=" + genre +
                ", mainQuest='" + mainQuest + '\'' +
                ", subQuest=" + subQuest +
                ", detail='" + detail + '\'' +
                ", scenarioAvatarList=" + scenarioAvatarList +
                ", worldParts=" + worldParts +
                ", tags=" + tags +
                '}';
    }
}
