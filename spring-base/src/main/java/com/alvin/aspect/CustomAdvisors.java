package com.alvin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CustomAdvisors {

  @Before("execution(public void com.alvin.service.UserService.test())")
  public void beforeAdvice() {
    log.info("方法执行前...");
  }
}
