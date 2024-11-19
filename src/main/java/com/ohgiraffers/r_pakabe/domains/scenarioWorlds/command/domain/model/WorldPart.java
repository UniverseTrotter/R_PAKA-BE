package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model;


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
public class WorldPart {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partId;

    @Column
    private String partName;

    @Column
    private Integer partType;

    @Column
//    @ColumnDefault("false")
    private Boolean isPortalEnable;

    @Column
    private Integer towardWorldPartId;

}
