package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.service;

import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model.ScenarioAvatar;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.service.ScenarioAvatarDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScenarioAvatarAppService {
    private final ScenarioAvatarDomainService scenarioAvatarDomainService;

    public ScenarioAvatarDTO loadAvatar(Integer avatarId) {
        ScenarioAvatar avatar = this.scenarioAvatarDomainService.getScenarioAvatar(avatarId);
        if (avatar == null){
           return ScenarioAvatarDTO.getEmpty();
        }else {
            return ScenarioAvatarDTO.fromEntity(avatar);
        }
    }

    public ScenarioAvatar uploadScenarioAvatar(ScenarioAvatarDTO avatarDTO) {

        ScenarioAvatar avatar = this.scenarioAvatarDomainService.getScenarioAvatar(avatarDTO.senarioAvatarId());
        if (avatar == null){
            avatar = scenarioAvatarDomainService.createScenarioAvatar(
                    ScenarioAvatar.builder()
                            .avatarName(avatarDTO.avatarName())
                            .outfit(avatarDTO.outfit())
                            .build()
            );
        }else {
            avatar = this.scenarioAvatarDomainService.updateScenarioAvatar(
                    new ScenarioAvatar(
                            avatar.getSenarioAvatarId(),
                            avatarDTO.avatarName(),
                            avatarDTO.outfit()
                    )
            );
        }
        return avatar;
    }

}
