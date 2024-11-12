package com.ohgiraffers.r_pakabe.flow.logic.service;

import com.ohgiraffers.r_pakabe.flow.logic.dto.ResponsePlayDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Slf4j
@Service
public class DiceService {

    private final Random rand;

    public DiceService (){
        rand = new Random();
    }

    public ResponsePlayDTO.DiceRollDTO rollDice() {
        rand.setSeed(System.currentTimeMillis());

        int diceFst = rand.nextInt(6) + 1;
        int diceSnd = rand.nextInt(6) + 1;

        return new ResponsePlayDTO.DiceRollDTO(diceFst, diceSnd);
    }
}
