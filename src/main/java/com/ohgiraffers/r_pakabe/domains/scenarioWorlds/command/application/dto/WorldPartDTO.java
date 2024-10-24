package com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model.WorldPart;

public record WorldPartDTO(
        Integer partId,
        String partName,
        boolean isPortalEnable
) {
    public static WorldPartDTO fromEntity(final WorldPart worldPart) {
        return new WorldPartDTO(
                worldPart.getPartId(),
                worldPart.getPartName(),
                worldPart.isPortalEnable()
        );
    }

    public static WorldPartDTO getEmpty() {
        return new WorldPartDTO(
                -1,
                "Empty",
                false
        );
    }
}
