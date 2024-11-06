package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.RequestAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model.ScenarioAvatar;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.service.ScenarioAvatarDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScenarioAvatarAppService {
    private final ScenarioAvatarDomainService scenarioAvatarDomainService;

//    public ScenarioAvatarDTO loadAvatar(Integer avatarId) {
//        ScenarioAvatar avatar = scenarioAvatarDomainService.getScenarioAvatar(avatarId);
//        if (avatar == null){
//           return ScenarioAvatarDTO.getEmpty();
//        }else {
//            return ScenarioAvatarDTO.fromEntity(avatar);
//        }
//    }

    /**
     * DTO를 보고 판단하기? 정말 필요한가?
     * */
    public ScenarioAvatar uploadScenarioAvatar(ScenarioAvatarDTO avatarDTO) {
        log.info("Upload scenario avatar : {}", avatarDTO);
        ScenarioAvatar avatar = scenarioAvatarDomainService.getScenarioAvatar(avatarDTO.scenarioAvatarId());
        if (avatar == null){
            avatar = scenarioAvatarDomainService.createScenarioAvatar(
                    ScenarioAvatar.builder()
                            .avatarName(avatarDTO.avatarName())
                            .outfit(avatarDTO.outfit())
                            .build()
            );
        }/*else {
            avatar = this.scenarioAvatarDomainService.updateScenarioAvatar(
                    new ScenarioAvatar(
                            avatar.getSenarioAvatarId(),
                            avatarDTO.avatarName(),
                            avatarDTO.outfit()
                    )
            );
        }*/
        return avatar;
    }

    @Transactional(readOnly = true)
    public List<ScenarioAvatarDTO> finAllAvatars() {
        List<ScenarioAvatar> avatars = scenarioAvatarDomainService.getAllScenarioAvatars();
        List<ScenarioAvatarDTO> avatarDTOS = new ArrayList<>();
        for (ScenarioAvatar avatar : avatars) {
            avatarDTOS.add(ScenarioAvatarDTO.fromEntity(avatar));
        }
        return avatarDTOS;
    }

    @Transactional(readOnly = true)
    public ScenarioAvatarDTO findScenarioAvatarById(Integer avatarId) {
        ScenarioAvatar avatar = scenarioAvatarDomainService.getScenarioAvatar(avatarId);
        if (avatar == null){
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }
        return ScenarioAvatarDTO.fromEntity(avatar);
    }

    @Transactional
    public ScenarioAvatarDTO createAvatar(RequestAvatarDTO.CreateAvatarDTO avatarDTO) {
        ScenarioAvatar avatar = ScenarioAvatar.builder()
                .avatarName(avatarDTO.avatarName())
                .outfit(avatarDTO.outfit())
                .isPlayable(avatarDTO.isPlayable())
                .build();
        avatar = scenarioAvatarDomainService.createScenarioAvatar(avatar);
        log.info("Create scenario avatar : {}", avatar);
        return ScenarioAvatarDTO.fromEntity(avatar);
    }

    @Transactional
    public ScenarioAvatarDTO updateAvatar(ScenarioAvatarDTO avatarDTO) {
        ScenarioAvatar avatar = scenarioAvatarDomainService.getScenarioAvatar(avatarDTO.scenarioAvatarId());
        if (avatar == null){
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }
        avatar = new ScenarioAvatar(
                avatar.getScenarioAvatarId(),
                avatarDTO.avatarName(),
                avatarDTO.outfit(),
                avatarDTO.isPlayalbe()
        );
        scenarioAvatarDomainService.updateScenarioAvatar(avatar);
        log.info("Update scenario avatar : {}", avatar);
        return ScenarioAvatarDTO.fromEntity(avatar);
    }

    public void deleteAvatar(Integer avatarId) {
        ScenarioAvatar avatar = scenarioAvatarDomainService.getScenarioAvatar(avatarId);
        if (avatar == null){
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }
        scenarioAvatarDomainService.deleteScenarioAvatar(avatarId);
        log.info("Delete scenario avatar : {}", avatar);
    }
}
