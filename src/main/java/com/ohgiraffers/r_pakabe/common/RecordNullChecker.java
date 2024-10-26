package com.ohgiraffers.r_pakabe.common;

import com.ohgiraffers.r_pakabe.common.error.NullfieldException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;

@Slf4j
public class RecordNullChecker {

    /**
     * 모든 필드에 입력을 요구할 때 사용
     * */
    public static void hasNullFields(Record record) throws Exception {
        RecordComponent[] components = record.getClass().getRecordComponents();
        for (RecordComponent component : components) {
            Method accessor = component.getAccessor();
            Object value = accessor.invoke(record);
            log.info("[Record Null Checker] 필드 이름: {}, 값: {}", component.getName(), value);
            if (value == null || "".equals(value) || "null".equals(value)) {
                throw new NullfieldException(
                        "필드가 입력되지 않았습니다 : " + component.getName());
            }
        }
    }
}
