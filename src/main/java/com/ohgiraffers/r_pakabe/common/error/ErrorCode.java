package com.ohgiraffers.r_pakabe.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "METARO-000", "Internal server error"),
    NO_SUCH_USER(HttpStatus.UNAUTHORIZED, "USER-001", "해당 ID로 회원가입이되어 있지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "USER-002", "비밀번호가 틀렸습니다."),
    EMAIL_STRUCTURE(HttpStatus.FORBIDDEN,"USER-003","이메일 형식으로 작성해주세요");

    private HttpStatus status;
    private String errorCode;
    private String message;
}
