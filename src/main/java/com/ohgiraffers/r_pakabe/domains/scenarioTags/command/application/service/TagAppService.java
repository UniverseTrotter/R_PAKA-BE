package com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto.ScenarioTagDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.model.ScenarioTag;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.service.TagDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagAppService {
    private final TagDomainService tagDomainService;

    public ScenarioTagDTO loadTag(Integer tagId) {
        ScenarioTag tag = this.tagDomainService.findTagByCode(tagId);
        if (tag == null) {
            return ScenarioTagDTO.getEmpty();
        }else {
            return ScenarioTagDTO.fromEntity(tag);
        }
    }

    public ScenarioTag uploadScenarioTag(ScenarioTagDTO tagDTO) {
        log.info("Upload scenario tag : {}", tagDTO);
        ScenarioTag tag = this.tagDomainService.findTagByCode(tagDTO.tagCode());
        if (tag == null) {
            tag = tagDomainService.createTag(
                    ScenarioTag.builder()
                            .tagName(tagDTO.tagName())
                            .build()
            );
        }else {
            tag = this.tagDomainService.updateTag(
                    new ScenarioTag(
                            tag.getTagCode(),
                            tagDTO.tagName())
            );
        }
        return tag;
    }

    public ScenarioTag creatTag(ScenarioTagDTO tagDTO) {
        log.info("Create scenario tag : {}", tagDTO);
        ScenarioTag tag = this.tagDomainService.findTagByCode(tagDTO.tagCode());
        if (tag != null) {
            throw new ApplicationException(ErrorCode.TAG_ALREADY_EXIT);
        }
        tag = ScenarioTag.builder()
                .tagName(tagDTO.tagName())
                .build();
        return tagDomainService.createTag(tag);
    }

    public ScenarioTag updateTag(ScenarioTagDTO tagDTO) {
        log.info("Update scenario tag : {}", tagDTO);
        ScenarioTag tag = this.tagDomainService.findTagByCode(tagDTO.tagCode());
        if (tag == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_TAG);
        }
        tag = this.tagDomainService.updateTag(
                new ScenarioTag(
                        tagDTO.tagCode(),
                        tagDTO.tagName()
                )
        );
        return tagDomainService.updateTag(tag);
    }



}
