package com.ohgiraffers.r_pakabe.domains.adventureLogs.command.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 후일담 저장
 * */
@ToString
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table
public class AdventureLog {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private Long roomNum;

    //플레이 기록
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "text[]")
    private List<String> history;


    // 사용된 시나리오 정보
    @Column
    private String scenarioTitle;

    @Column
    private String mainQuest;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "text[]")
    private List<String> subQuest;

    @Column
    private String detail;


    @CreatedDate
    private LocalDateTime createdAt;

}
