package com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto.ScenarioTagDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.model.ScenarioTag;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.service.TagDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagAppService {
    private final TagDomainService tagDomainService;

//    public ScenarioTagDTO loadTag(Integer tagId) {
//        ScenarioTag tag = this.tagDomainService.findTagByCode(tagId);
//        if (tag == null) {
//            return ScenarioTagDTO.getEmpty();
//        }else {
//            return ScenarioTagDTO.fromEntity(tag);
//        }
//    }

    public ScenarioTag uploadScenarioTag(ScenarioTagDTO tagDTO) {
        log.info("Upload scenario tag : {}", tagDTO);
        ScenarioTag tag = this.tagDomainService.findTagByCode(tagDTO.tagCode());
        if (tag == null) {
            tag = creatTag(tagDTO);
        }
        return tag;
    }

    public ScenarioTag creatTag(ScenarioTagDTO tagDTO) {
        //name 기준으로 판정
        log.info("Create scenario tag : {}", tagDTO);
        ScenarioTag tag = tagDomainService.findTagByName(tagDTO.tagName());
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


    @Transactional(readOnly = true)
    public List<ScenarioTagDTO> getAllTags() {
        List<ScenarioTag> tags = tagDomainService.findTagAll();
        List<ScenarioTagDTO> tagDTOList = new ArrayList<>();
        for (ScenarioTag tag : tags) {
            tagDTOList.add(ScenarioTagDTO.fromEntity(tag));
        }
        return tagDTOList;
    }

    @Transactional(readOnly = true)
    public ScenarioTagDTO findTagById(Integer tagId) {
        ScenarioTag tag = this.tagDomainService.findTagByCode(tagId);
        if (tag == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_TAG);
        }
        return ScenarioTagDTO.fromEntity(tag);
    }

    @Transactional(readOnly = true)
    public ScenarioTagDTO findTagByName(String tagName) {
        ScenarioTag tag = this.tagDomainService.findTagByName(tagName);
        if (tag == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_TAG);
        }
        return ScenarioTagDTO.fromEntity(tag);
    }

    @Transactional
    public void creatTag(String tagName) {
        ScenarioTag tag = this.tagDomainService.findTagByName(tagName);
        if (tag != null) {
            throw new ApplicationException(ErrorCode.TAG_ALREADY_EXIT);
        }
        tag = tagDomainService.createTag(new ScenarioTag(tagName));
        log.info("Create Tag : {}", tag);
    }

    @Transactional
    public void deleteTag(Integer tagId) {
        ScenarioTag tag = this.tagDomainService.findTagByCode(tagId);
        if (tag == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_TAG);
        }
        tagDomainService.deleteTag(tagId);
        log.info("Delete Tag ID : {}", tag);
    }
}
