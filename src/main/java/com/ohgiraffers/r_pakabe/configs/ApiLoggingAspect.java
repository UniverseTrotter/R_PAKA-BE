package com.ohgiraffers.r_pakabe.configs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ApiLoggingAspect.class);

    @Pointcut("within(com.ohgiraffers.r_pakabe..*) && within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerMethods() {}

    @Before("restControllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("[{}] API called: ({}) with args: {}",
                getSimpleClassName(joinPoint),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(value = "restControllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("[{}] API response: ({}) with result: {}",
                getSimpleClassName(joinPoint),
                joinPoint.getSignature().getName(),
                result);
    }

//    @AfterThrowing(value = "restControllerMethods()", throwing = "ex")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
//        logger.error("API exception in: {}.{} with message: {}",
//                getSimpleClassName(joinPoint),
//                joinPoint.getSignature().getName(),
//                ex.getMessage());
//    }

    @Around("restControllerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime(); // 요청 시작 시간

        Object result;
        try {
            result = joinPoint.proceed(); // 실제 메서드 실행
        } finally {     //예외가 발생 하든 안하든 실행
            long endTime = System.nanoTime(); // 요청 종료 시간
            long duration = (endTime - startTime) / 1_000_000; // 밀리초 단위로 변환

            logger.info("[{}] API execution time: ({}) took {} ms",
                    getSimpleClassName(joinPoint),
                    joinPoint.getSignature().getName(),
                    duration);
        }

        return result;
    }

    private String getSimpleClassName(JoinPoint joinPoint) {
        // 패키지명을 제외한 클래스명만 반환
        return joinPoint.getSignature().getDeclaringType().getSimpleName();
    }
}
