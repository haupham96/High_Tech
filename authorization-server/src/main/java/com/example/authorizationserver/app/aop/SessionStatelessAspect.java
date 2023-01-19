package com.example.authorizationserver.app.aop;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class SessionStatelessAspect {
    private final HttpSession httpSession;

    @AfterReturning(pointcut = "execution(public * com.example.authorizationserver.app.controller.*.*(..))")
    public void invalidateSession(JoinPoint joinPoint) {
        log.info("class {}", joinPoint.getTarget().getClass().getSimpleName());
        log.info("method : {}", joinPoint.getSignature().getName());
        log.info("invalidate session ...");
        httpSession.invalidate();
    }
}
