package com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.service;

import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto.ScenarioTagDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.model.ScenarioTag;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.service.TagDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
