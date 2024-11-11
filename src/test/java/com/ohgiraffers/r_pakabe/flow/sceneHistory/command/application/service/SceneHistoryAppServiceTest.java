package com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.service;

import com.ohgiraffers.r_pakabe.RPakaBeApplication;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.RequestHistoryDTO;
import com.ohgiraffers.r_pakabe.flow.sceneHistory.command.application.dto.SceneHistoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RPakaBeApplication.class)
class SceneHistoryAppServiceTest {

    @Autowired
    private SceneHistoryAppService appService;

    @Test
    void createHistory() {

        SceneHistoryDTO created = appService.createHistory(makeDTO());

        assertNotNull(created);
    }

    private RequestHistoryDTO.createDTO makeDTO(){
        return new RequestHistoryDTO.createDTO(
                1,
                "test"
        );
    }
}