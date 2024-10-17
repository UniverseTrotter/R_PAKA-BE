package com.ohgiraffers.r_pakabe.common.error;

import com.ohgiraffers.r_pakabe.common.PolyTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class NullfieldException extends RuntimeException {

    private HttpStatus status;
    private String errorCode;
    private String message;
    private String timestamp;

    public NullfieldException(String message){
        this.status = HttpStatus.BAD_REQUEST;
        this.errorCode = "REQUEST-001";
        this.message = message;
        this.timestamp = new PolyTime(LocalDateTime.now()).get();
    }
}