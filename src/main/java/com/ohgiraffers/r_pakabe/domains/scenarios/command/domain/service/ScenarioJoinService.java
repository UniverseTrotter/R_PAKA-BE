package com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.genres.command.application.service.GenreAppService;
import com.ohgiraffers.r_pakabe.domains.genres.command.domain.model.Genre;
import com.ohgiraffers.r_pakabe.domains.genres.command.domain.service.GenreDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.service.ScenarioAvatarAppService;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model.ScenarioAvatar;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.service.ScenarioAvatarDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.service.TagAppService;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.service.WorldPartAppService;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.model.WorldPart;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.domain.service.WorldPartDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto.ScenarioDTO;
import com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model.Scenario;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto.ScenarioTagDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.model.ScenarioTag;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.domain.service.TagDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScenarioJoinService {


    private final ScenarioAvatarAppService scenarioAvatarAppService;
    private final WorldPartAppService worldPartAppService;
    private final GenreAppService genreAppService;
    private final TagAppService tagAppService;


    public ScenarioDTO getDataFromEntity(Scenario scenario){
        List<Integer> avatarIndex = scenario.getScenarioAvatarList();
        List<Integer> worldIndex = scenario.getWorldParts();
        List<Integer> genreIndex = scenario.getGenre();
        List<Integer> tagIndex = scenario.getTags();

        List<ScenarioAvatarDTO> avatarList = new ArrayList<>();
        List<WorldPartDTO> worldPartList = new ArrayList<>();
        List<GenreDTO> genreList = new ArrayList<>();
        List<ScenarioTagDTO> tagList = new ArrayList<>();

        for (int index : avatarIndex){
            avatarList.add(scenarioAvatarAppService.loadAvatar(index));
        }

        for (int index : worldIndex){
            worldPartList.add(worldPartAppService.loadWorldPart(index));
        }

        for (int index : genreIndex){
            genreList.add(genreAppService.loadGenre(index));
        }

        for (int index : tagIndex){
            tagList.add(tagAppService.loadTag(index));
        }

        return new ScenarioDTO(
                scenario.getScenarioCode(),
                scenario.getScenarioTitle(),
                genreList,
                scenario.getMainQuest(),
                scenario.getSubQuest(),
                scenario.getDetail(),
                avatarList,
                worldPartList,
                tagList
        );
    }


    public Scenario createEntityFromDTO(ScenarioDTO scenarioDTO){
        List<ScenarioAvatarDTO> avatarList = scenarioDTO.scenarioAvatarList();
        List<WorldPartDTO> worldPartList = scenarioDTO.worldParts();
        List<GenreDTO> genreList = scenarioDTO.genre();
        List<ScenarioTagDTO> tagList = scenarioDTO.tags();

        List<Integer> avatarIndex = new ArrayList<>();
        List<Integer> worldIndex = new ArrayList<>();
        List<Integer> genreIndex = new ArrayList<>();
        List<Integer> tagIndex = new ArrayList<>();

        for (ScenarioAvatarDTO avatarDTO : avatarList){
            ScenarioAvatar avatar = this.scenarioAvatarAppService.uploadScenarioAvatar(avatarDTO);
            avatarIndex.add(avatar.getSenarioAvatarId());
        }

        for (WorldPartDTO worldPartDTO : worldPartList){
            WorldPart worldPart = this.worldPartAppService.uploadWorldPart(worldPartDTO);
            worldIndex.add(worldPart.getPartId());
        }

        for (GenreDTO genreDTO : genreList){
            Genre genre = this.genreAppService.uploadGenre(genreDTO);
            genreIndex.add(genre.getGenreCode());
        }

        for (ScenarioTagDTO tagDTO : tagList){
            ScenarioTag tag = this.tagAppService.uploadScenarioTag(tagDTO);
            tagIndex.add(tag.getTagCode());
        }

        return Scenario.builder()
                .scenarioTitle(scenarioDTO.scenarioTitle())
                .genre(genreIndex)
                .mainQuest(scenarioDTO.mainQuest())
                .subQuest(scenarioDTO.subQuest())
                .detail(scenarioDTO.detail())
                .scenarioAvatarList(avatarIndex)
                .worldParts(worldIndex)
                .tags(tagIndex)
                .build();
    }
}
