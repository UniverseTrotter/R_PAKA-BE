package com.ohgiraffers.r_pakabe.domains.scenarios.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioAvatars.command.application.dto.ScenarioAvatarDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioTags.command.application.dto.ScenarioTagDTO;
import com.ohgiraffers.r_pakabe.domains.scenarioWorlds.command.application.dto.WorldPartDTO;

import java.util.List;

public class RequestScenarioDTO {
    public record ScenarioIdDTO(
            Long scenarioCode
    ) {}

    public record UploadScenarioDTO(
            String scenarioTitle,
            String mainQuest,
            List<String> subQuest,
            String detail,
            List<ScenarioAvatarDTO> scenarioAvatarList,
            List<WorldPartDTO> worldParts,
            List<GenreDTO> genre,
            List<ScenarioTagDTO> tags
    ){}


    public record CreateScenarioDTO(
            String scenarioTitle,
            String mainQuest,
            List<String> subQuest,
            String detail,
            List<Integer> scenarioAvatarList,
            List<Integer> worldParts,
            List<String> genre,
            List<String> tags
    ){

    }

    public record NewScenarioDTO(
            Long scenarioCode,
            String scenarioTitle,
            String mainQuest,
            List<String> subQuest,
            String detail,
            List<Integer> scenarioAvatarList,
            List<Integer> worldParts,
            List<String> genre,
            List<String> tags
    ){
        public static NewScenarioDTO insertCreateCode(CreateScenarioDTO createDTO){
            return new NewScenarioDTO(
                    -1L,    //create code
                    createDTO.scenarioTitle(),
                    createDTO.mainQuest(),
                    createDTO.subQuest(),
                    createDTO.detail(),
                    createDTO.scenarioAvatarList(),
                    createDTO.worldParts(),
                    createDTO.genre(),
                    createDTO.tags()
            );
        }
    }

}
