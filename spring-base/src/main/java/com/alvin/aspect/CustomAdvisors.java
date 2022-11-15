package com.alvin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CustomAdvisors {

  @Before("execution(public void com.alvin.service.UserService.test())")
  public void beforeAdvice() {
    log.info("Before: 方法执行前...");
  }

  @Around("execution(public void com.alvin.service.UserService.test())")
  public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
    log.info("Around: 方法执行前");
    Object result = pjp.proceed();
    log.info("Around: 方法执行后");
    return result;
  }
}
