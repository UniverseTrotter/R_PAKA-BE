package com.ohgiraffers.r_pakabe.domains.user.command.application.dto;

public class UserResponseDTO {

    /**
     * 로그인 했을때 리턴해줘야하는 정보
     * */
    public record authDTO(
            Long userCode
    ){}

    /**
     * 회원 정보 리턴
     * */
    public record UserDetailDTO(
            String userId,
            String nickname
    ){}

}
