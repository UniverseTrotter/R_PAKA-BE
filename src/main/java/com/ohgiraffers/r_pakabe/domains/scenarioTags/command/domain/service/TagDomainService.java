package com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.model.ScenarioTag;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagDomainService {
    private final TagRepository tagRepository;

    public TagDomainService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public ScenarioTag findTagByCode(Integer tagCode) {
        return tagRepository.findById(tagCode).get();
    }

    public ScenarioTag findTagByName(String name) {
        return tagRepository.findByTagName(name).orElse(null);
    }

    public List<ScenarioTag> findTagAll() {
        return tagRepository.findAll();
    }

    public ScenarioTag createTag(ScenarioTag tag) {
        return tagRepository.save(tag);
    }

    public ScenarioTag saveTag(ScenarioTag scenarioTag) {
        return tagRepository.save(scenarioTag);
    }

    public ScenarioTag updateTag(ScenarioTag scenarioTag) {
        return tagRepository.save(scenarioTag);
    }

    public void deleteTag(Integer tagCode) {
        tagRepository.deleteById(tagCode);
    }
}
