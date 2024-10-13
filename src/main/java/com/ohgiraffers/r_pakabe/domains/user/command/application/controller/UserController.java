package com.ohgiraffers.r_pakabe.domains.user.command.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "유저 API", description = "회원가입, 유저 정보 조회 API")
public class UserController {
}
