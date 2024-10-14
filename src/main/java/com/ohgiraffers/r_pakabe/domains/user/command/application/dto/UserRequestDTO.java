package com.ohgiraffers.r_pakabe.domains.user.command.application.dto;

public class UserRequestDTO {


    /**
     * 로그인할 때, 요청하는 데이터
     * */
    public record LoginDTO(
            String userId,
            String password
    ) {}


}
