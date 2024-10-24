package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault("여기에 장소명을 입력")
    private String partName;

    @Column
    @ColumnDefault("false")
    private boolean isPortalEnable;

    @Builder
    public WorldPart(String partName, boolean isPortalEnable) {
        this.partName = partName;
        this.isPortalEnable = isPortalEnable;
    }

    @Override
    public String toString() {
        return "WorldPart{" +
                "partId=" + partId +
                ", partName='" + partName + '\'' +
                ", isPortalEnable=" + isPortalEnable +
                '}';
    }
}