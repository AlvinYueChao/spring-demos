package org.example.alvin.tuling.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect {

  @Before("execution(public void org.example.alvin.tuling.service.UserService.test())")
  public void myBefore(JoinPoint joinPoint) {
    log.info("my before advise, {}", joinPoint);
  }
}
