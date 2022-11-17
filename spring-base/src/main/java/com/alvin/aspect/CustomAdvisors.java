package com.alvin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
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
  public Object aroundAdvice(ProceedingJoinPoint pjp) {
    log.info("Around: 方法执行前");
    Object result = null;
    try {
      result = pjp.proceed();
    } catch (Throwable e) {
      log.warn("Around: 捕获异常");
    }
    log.info("Around: 方法执行后");
    return result;
  }

  @After("execution(public void com.alvin.service.UserService.test())")
  public void afterAdvice() {
    log.info("After: 方法执行后");
  }
}
