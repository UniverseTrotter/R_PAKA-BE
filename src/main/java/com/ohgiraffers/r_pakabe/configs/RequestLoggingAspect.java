package com.ohgiraffers.r_pakabe.configs;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Aspect
@Component
public class RequestLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);

    @Pointcut("execution(* com.ohgiraffers.r_pakabe.flow.aiComm.service.AiConnectionService.*(..))")
    public void aiRequestMethod() {}

    @Before("aiRequestMethod()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("[AiConnectionService] ({}) Sending Request with args: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(value = "aiRequestMethod()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("[AiConnectionService] ({}) is detached",
                joinPoint.getSignature().getName());
    }

    @Around("aiRequestMethod()")
    public Object logExecutionTimeAsync(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();

        Object result = null;

        try {
            result = joinPoint.proceed();
        }finally {
            if (result instanceof Mono) {   // 혹시 몰라서 타입 체크
                result = ((Mono<?>) result)
                        .doOnSubscribe(subscription -> {
                            logger.info("[WebClient-AI-Request] ({}) Subscription started",
                                    joinPoint.getSignature().getName());
                        })
                        .doOnSuccess(response -> {
                            long duration = (System.nanoTime() - startTime) / 1_000_000;
                            logger.info("[WebClient-AI-Response] ({}) Answer Received in {} ms",
                                    joinPoint.getSignature().getName(),
                                    duration);
                            logger.info("[AiConnectionService] ({}) got response: {} ",
                                    joinPoint.getSignature().getName(),
                                    response
                            );
                        });
            }else {
                long duration = (System.nanoTime() - startTime) / 1_000_000;
                logger.info("[AiConnectionService] ({}) executed in {} ms",
                        joinPoint.getSignature().getName(),
                        duration);
            }
        }

        if (result == null){
            throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return result;
    }


}
