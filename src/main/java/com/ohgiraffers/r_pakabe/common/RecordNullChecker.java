package com.ohgiraffers.r_pakabe.common;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;

import java.lang.reflect.RecordComponent;

public class RecordNullChecker {
    public static void hasNullFields(Record record) throws ApplicationException {
        RecordComponent[] components = record.getClass().getRecordComponents();
        for (RecordComponent component : components) {
            if (component != null) {
                throw new ApplicationException(ErrorCode.BAD_REQUEST_DATA,
                        "필드가 입력되지 않았습니다 : " + component);
            }
        }
    }
}
