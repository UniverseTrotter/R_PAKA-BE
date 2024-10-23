package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model;


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
public class WorldPart {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId;

    @Column
    @ColumnDefault("여기에 장소명을 입력")
    private String partName;

    @Column
    @ColumnDefault("false")
    private boolean isPortalEnable;

    @Override
    public String toString() {
        return "WorldPart{" +
                "partId=" + partId +
                ", partName='" + partName + '\'' +
                ", isPortalEnable=" + isPortalEnable +
                '}';
    }
}
