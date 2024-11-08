package com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.RequestSettingDTO;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.UserScenarioSettingDTO;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.application.dto.UserScenarioSettingMapper;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.domain.model.UserScenarioSetting;
import com.ohgiraffers.r_pakabe.domains.userScenarioSettings.command.domain.service.UserSettingDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserScenarioSettingAppService {

    private final UserSettingDomainService domainService;
    private final UserScenarioSettingMapper mapper;


    @Transactional(readOnly = true)
    public List<UserScenarioSettingDTO> findAllList() {
        List<UserScenarioSetting> entityList = domainService.getAllSettings();
        List<UserScenarioSettingDTO> result = new ArrayList<>();
        for (UserScenarioSetting entity : entityList) {
            result.add(mapper.entityToDto(entity));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<UserScenarioSettingDTO> findAllByUserCode(Long userCode) {
        List<UserScenarioSetting> entityList = domainService.getUserSettingByUserCode(userCode);
        if (entityList.isEmpty()) {
            throw new ApplicationException(ErrorCode.NO_SUCH_SETTING);
        }
        List<UserScenarioSettingDTO> result = new ArrayList<>();
        for (UserScenarioSetting entity : entityList) {
            result.add(mapper.entityToDto(entity));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public UserScenarioSettingDTO findSetting(RequestSettingDTO.findOneDTO requestDto) {
        UserScenarioSettingDTO dto = mapper.entityToDto(
                domainService.getUserSettingByUserCodeAndScenarioCode(
                        requestDto.userCode(),
                        requestDto.scenarioCode()
                )
        );
        if (dto == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_SETTING);
        }
        return dto;
    }

    @Transactional
    public UserScenarioSettingDTO uploadUserSetting(RequestSettingDTO.uploadDTO uploadDTO) {
        UserScenarioSetting entity = domainService.getUserSettingByUserCodeAndScenarioCode(
                uploadDTO.userCode(),
                uploadDTO.scenarioCode()
        );
        if (entity == null) {
            entity = domainService.createUserSetting(mapper.uploadDtoToEntity(uploadDTO));
            log.info("setting not found, setting created : {}", entity);
        }else {
            UserScenarioSettingDTO dto = mapper.entityToDto(entity);
            mapper.updateDtoFromUploadDTO(uploadDTO, dto);
            entity = domainService.updateUserSetting(mapper.dtoToEntity(dto));
            log.info("setting updated : {}", entity);
        }
        return mapper.entityToDto(entity);
    }

    @Transactional
    public void deleteSetting(RequestSettingDTO.findOneDTO findOneDTO) {
        UserScenarioSetting setting = domainService.getUserSettingByUserCodeAndScenarioCode(
                findOneDTO.userCode(),
                findOneDTO.scenarioCode()
        );
        if (setting != null) {
            domainService.deleteUserSetting(setting.getSettingId());
            log.info("setting deleted : {}", setting);
        }

    }
}
