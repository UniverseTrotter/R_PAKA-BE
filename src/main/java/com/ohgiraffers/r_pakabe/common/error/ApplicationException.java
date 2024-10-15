package com.ohgiraffers.r_pakabe.common.error;

import com.ohgiraffers.r_pakabe.common.PolyTime;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String timestamp;

    public ApplicationException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.timestamp = new PolyTime(LocalDateTime.now()).get();
    }

    public ApplicationException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
        this.timestamp = new PolyTime(LocalDateTime.now()).get();
    }
}