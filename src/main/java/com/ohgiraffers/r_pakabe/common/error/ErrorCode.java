package com.ohgiraffers.r_pakabe.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "METARO-000", "Internal server error"),
    NO_SUCH_ID(HttpStatus.UNAUTHORIZED, "USER-001", "해당 ID로 회원가입이되어 있지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "USER-002", "비밀번호가 틀렸습니다."),
    ID_IS_EMPTY(HttpStatus.BAD_REQUEST,"USER-003","ID가 입력되지 않았습니다."),
    PW_IS_EMPTY(HttpStatus.BAD_REQUEST,"USER-004","PW가 입력되지 않았습니다."),
    ID_ALREADY_EXIT(HttpStatus.BAD_REQUEST,"USER-005","이미 존재하는 ID 입니다."),
    BAD_USER_DATA(HttpStatus.BAD_REQUEST,"USER-006","입력값이 올바르지 않습니다."),
    USER_NOT_EXIST(HttpStatus.UNAUTHORIZED, "USER-007", "해당 유저가 없습니다."),
    NO_SUCH_AVATAR(HttpStatus.BAD_REQUEST,"AVATAR-001","해당 아바타가 없습니다."),
    AVATAR_ALREADY_EXIST(HttpStatus.BAD_REQUEST,"AVATAR-002", "해당 아바타가 이미 존재합니다."),
    NO_SUCH_SCENARIO(HttpStatus.BAD_REQUEST,"SCENARIO-001" , "해당 시나리오가 없습니다."),
    SCENARIO_ALREADY_EXIT(HttpStatus.BAD_REQUEST, "SCENARIO-002", "해당 제목의 시나리오가 이미 있습니다."),
    TAG_ALREADY_EXIT(HttpStatus.BAD_REQUEST, "TAG-001", "해당 태그가 이미 있습니다."),
    NO_SUCH_TAG(HttpStatus.BAD_REQUEST, "TAG-002", "해당 태그가 없습니다."),
    GENRE_ALREADY_EXIT(HttpStatus.BAD_REQUEST, "GENRE-001", "해당 장르가 이미 있습니다."),
    NO_SUCH_GENRE(HttpStatus.BAD_REQUEST, "GENRE-002", "해당 장르가 없습니다."),
    WORLD_ALREADY_EXIT(HttpStatus.BAD_REQUEST, "WORLD-001", "해당 ID의 월드가 이미 있습니다."),
    NO_SUCH_WORLD(HttpStatus.BAD_REQUEST, "WORLD-002", "해당 월드가 없습니다."),
    NO_SUCH_SETTING(HttpStatus.BAD_REQUEST, "SCENARIO-003", "해당 시나리오에 유저의 세팅이 없습니다."),
    REDUNDANT_ROOM_NUMBER(HttpStatus.BAD_REQUEST, "RUNNING-001", "같은 번호의 방이 이미 진행중 입니다."),
    NO_SUCH_ROOM(HttpStatus.BAD_REQUEST, "RUNNING-002", "해당 번호의 방이 존재하지 않습니다."),
    CANNOT_HANDLE_EVENT(HttpStatus.UNPROCESSABLE_ENTITY, "PLAY-001", "예상치 못한 이벤트 판정입니다."),
    INVALID_DICE(HttpStatus.BAD_REQUEST, "PLAY-002", "올바르지 않은 주사위 수치 입니다."),
    NO_DIALOG(HttpStatus.BAD_REQUEST, "PLAY-003", "대화 기록이 없습니다."),
    EMPTY_RESPONSE(HttpStatus.BAD_REQUEST, "PLAY-004", "대답이 오지 않았습니다."),

    ;


    private HttpStatus status;
    private String errorCode;
    private String message;
}
