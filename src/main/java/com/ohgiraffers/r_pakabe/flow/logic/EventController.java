package com.ohgiraffers.r_pakabe.flow.logic;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/running")
@Tag(name = "진행중인 시나리오", description = "시나리오 조회")
public class EventController {
}
