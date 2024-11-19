package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorldPartDTO{
    private Integer partId;
    private String partName;
    private Integer partType;
    private Boolean isPortalEnable;
    private Integer towardWorldPartId;
}
