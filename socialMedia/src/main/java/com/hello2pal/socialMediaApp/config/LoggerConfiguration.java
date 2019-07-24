package com.hello2pal.socialMediaApp.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@Aspect
@Configuration
public class LoggerConfiguration {

    private static final String REF_ID = "refId";

    @Before("execution(* com.hello2pal.socialMediaApp.controller..*.*(..))")
    public void mdcPut(JoinPoint joinPoint) {
        MDC.put(REF_ID, UUID.randomUUID().toString().replace("-", "1").substring(0, 20));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute(REF_ID, MDC.get(REF_ID));
    }

    @After("execution(* com.hello2pal.socialMediaApp.controller..*.*(..))")
    public void mdcRemove(JoinPoint joinPoint) {
        log.info("Clearing MDC");
        MDC.clear();
    }
}
