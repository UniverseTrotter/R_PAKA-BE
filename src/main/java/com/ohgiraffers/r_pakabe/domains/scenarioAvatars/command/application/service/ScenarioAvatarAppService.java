package com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.RequestAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarMapper;
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
    private final ScenarioAvatarMapper mapper;

    /**
     * 없으면 만듦
     * */
    public ScenarioAvatar uploadScenarioAvatar(ScenarioAvatarDTO avatarDTO) {
        ScenarioAvatar avatar = scenarioAvatarDomainService.getScenarioAvatar(avatarDTO.scenarioAvatarId());
        if (avatar == null){
            avatar = mapper.toScenarioAvatarEntity(
                    mapper.toCreateAvatarDTO(avatarDTO)      // userCode 를 제거
            );
            avatar = scenarioAvatarDomainService.createScenarioAvatar(avatar);
            log.info("Create scenario avatar Because not found : {}", avatar);
        }
        return avatar;
    }

    @Transactional(readOnly = true)
    public List<ScenarioAvatarDTO> finAllAvatars() {
        List<ScenarioAvatar> avatars = scenarioAvatarDomainService.getAllScenarioAvatars();
        List<ScenarioAvatarDTO> avatarDTOS = new ArrayList<>();
        for (ScenarioAvatar avatar : avatars) {
            avatarDTOS.add(mapper.toScenarioAvatarDTO(avatar));
        }
        return avatarDTOS;
    }

    @Transactional(readOnly = true)
    public ScenarioAvatarDTO findScenarioAvatarById(Integer avatarId) {
        ScenarioAvatar avatar = scenarioAvatarDomainService.getScenarioAvatar(avatarId);
        if (avatar == null){
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }
        return mapper.toScenarioAvatarDTO(avatar);
    }

    @Transactional
    public ScenarioAvatarDTO createAvatar(RequestAvatarDTO.CreateAvatarDTO avatarDTO) {
        ScenarioAvatar avatar = mapper.toScenarioAvatarEntity(avatarDTO);
        avatar = scenarioAvatarDomainService.createScenarioAvatar(avatar);
        log.info("Create scenario avatar : {}", avatar);
        return mapper.toScenarioAvatarDTO(avatar);
    }

    @Transactional
    public ScenarioAvatarDTO updateAvatar(ScenarioAvatarDTO avatarDTO) {
        ScenarioAvatar avatar = scenarioAvatarDomainService.getScenarioAvatar(avatarDTO.scenarioAvatarId());
        if (avatar == null){
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }

        avatar = mapper.toScenarioAvatarEntity(avatarDTO);

        scenarioAvatarDomainService.updateScenarioAvatar(avatar);
        log.info("Update scenario avatar : {}", avatar);
        return mapper.toScenarioAvatarDTO(avatar);
    }

    @Transactional
    public void deleteAvatar(Integer avatarId) {
        ScenarioAvatar avatar = scenarioAvatarDomainService.getScenarioAvatar(avatarId);
        if (avatar == null){
            throw new ApplicationException(ErrorCode.NO_SUCH_AVATAR);
        }
        scenarioAvatarDomainService.deleteScenarioAvatar(avatarId);
        log.info("Delete scenario avatar : {}", avatar);
    }
}
