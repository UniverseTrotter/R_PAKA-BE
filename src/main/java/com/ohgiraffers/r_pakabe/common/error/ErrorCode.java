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
    ID_IS_EMPTY(HttpStatus.BAD_REQUEST,"USER-003","ID가 입력되지 않았습니다."),
    PW_IS_EMPTY(HttpStatus.BAD_REQUEST,"USER-004","PW가 입력되지 않았습니다."),
    ID_ALREADY_EXIT(HttpStatus.BAD_REQUEST,"USER-005","이미 존재하는 ID 입니다."),
    BAD_USER_DATA(HttpStatus.BAD_REQUEST,"USER-006","입력값이 올바르지 않습니다.");

    private HttpStatus status;
    private String errorCode;
    private String message;
}
