package com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.genres.command.domain.model.Genre;
import com.ohgiraffers.r_pakabe.domains.genres.command.domain.service.GenreDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.model.ScenarioAvatar;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.domain.service.ScenarioAvatarDomainService;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;
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


    private final ScenarioAvatarDomainService scenarioAvatarDomainService;
    private final WorldPartDomainService worldPartDomainService;
    private final GenreDomainService genreDomainService;
    private final TagDomainService tagDomainService;


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
            ScenarioAvatar avatar = this.scenarioAvatarDomainService.getScenarioAvatar(index);
            if (avatar == null){
                avatarList.add(ScenarioAvatarDTO.getEmpty());
            }else {
                avatarList.add(ScenarioAvatarDTO.fromEntity(avatar));
            }
        }

        for (int index : worldIndex){
            WorldPart worldPart = this.worldPartDomainService.getWorldPart(index);
            if (worldPart == null){
                worldPartList.add(WorldPartDTO.getEmpty());
            }else {
                worldPartList.add(WorldPartDTO.fromEntity(worldPart));
            }
        }

        for (int index : genreIndex){
            Genre genre = this.genreDomainService.findGenreById(index);
            if (genre == null){
                genreList.add(GenreDTO.getEmpty());
            }else {
                genreList.add(GenreDTO.formEntity(genre));
            }
        }

        for (int index : tagIndex){
            ScenarioTag scenarioTag = this.tagDomainService.findTagByCode(index);
            if (scenarioTag == null){
                tagList.add(ScenarioTagDTO.getEmpty());
            }else{
                tagList.add(ScenarioTagDTO.fromEntity(scenarioTag));
            }
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
            ScenarioAvatar avatar = this.scenarioAvatarDomainService.getScenarioAvatar(avatarDTO.senarioAvatarId());
            if (avatar == null){
                scenarioAvatarDomainService.createScenarioAvatar(
                        ScenarioAvatar.builder()
                                .avatarName(avatarDTO.avatarName())
                                .outfit(avatarDTO.outfit())
                                .build()
                );
            }else {
                this.scenarioAvatarDomainService.updateScenarioAvatar(
                        new ScenarioAvatar(
                                avatar.getSenarioAvatarId(),
                                avatarDTO.avatarName(),
                                avatarDTO.outfit()
                        )
                );
            }
        }






        return null;
    }
}
