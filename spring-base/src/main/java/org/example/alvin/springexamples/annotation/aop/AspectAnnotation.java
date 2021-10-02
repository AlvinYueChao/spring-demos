package org.example.alvin.springexamples.annotation.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectAnnotation {

  private final Logger logger = LogManager.getLogger(AspectAnnotation.class);

  @Pointcut("execution(public * org.example.alvin.springexamples.annotation.spi.service.*.*(..))")
  private void pc1() {}

  @Around("pc1()")
  private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    logger.info("====== AspectAnnotation aroundǰ��֪ͨ ======");
    Object result = joinPoint.proceed();
    logger.info("====== AspectAnnotation around����֪ͨ ======");
    return result;
  }
}
