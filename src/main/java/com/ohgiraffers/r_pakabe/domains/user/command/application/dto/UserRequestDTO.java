package com.ohgiraffers.r_pakabe.domains.user.command.application.dto;

public class UserRequestDTO {

    public record LoginDTO(
            String userId,
            String password
    ) {}


}
