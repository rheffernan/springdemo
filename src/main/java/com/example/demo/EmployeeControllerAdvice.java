package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;



@Aspect
@Configuration
public class EmployeeControllerAdvice {


    private Logger logger = Logger.getLogger(EmployeeControllerAdvice.class.getName());

    @Before("execution(public * com.example.demo.*.*(..))")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();

        if (logger != null && methodName != null) {
            logger.info("Before " + methodName);
        }
    }
}
