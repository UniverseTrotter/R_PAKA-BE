package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model.WorldPart;

public record WorldPartDTO(
        Integer partId,
        String partName,
        boolean isPortalEnable,
        Integer towardWorldPartId
) {
    public static WorldPartDTO fromEntity(final WorldPart worldPart) {
        return new WorldPartDTO(
                worldPart.getPartId(),
                worldPart.getPartName(),
                worldPart.getIsPortalEnable(),
                worldPart.getTowardWorldPartId()
        );
    }

    public static WorldPartDTO getEmpty() {
        return new WorldPartDTO(
                -1,
                "Empty",
                false,
                -1
        );
    }
}
