package com.ohgiraffers.r_pakabe.flow.logic.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class EventServiceTest {

    @Autowired
    private EventService eventService;

    @ParameterizedTest
    @CsvSource({
            "0, 7, false",  // 보정치 -2 -> diceNum=5 -> 실패
            "1, 9, true",   // 보정치 -2 -> diceNum=7 -> 성공
            "2, 7, false",  // 보정치 -1 -> diceNum=6 -> 실패
            "3, 8, true",   // 보정치 -1 -> diceNum=7 -> 성공
            "4, 6, false",  // 보정치 +0 -> diceNum=6 -> 실패
            "5, 7, true",   // 보정치 +0 -> diceNum=7 -> 성공
            "6, 6, true",   // 보정치 +1 -> diceNum=7 -> 성공
            "7, 5, false",  // 보정치 +1 -> diceNum=6 -> 실패
            "8, 4, false",  // 보정치 +2 -> diceNum=6 -> 실패
            "8, 5, true"    // 보정치 +2 -> diceNum=7 -> 성공
    })
    void testIsDiceSuccess(int status, int diceNum, boolean expected) {
        boolean result = eventService.isDiceSuccess(status, diceNum);

        assertThat(result)
                .withFailMessage("Failed for status=%d, diceNum=%d, expected=%b", status, diceNum, expected)
                .isEqualTo(expected);
    }
}