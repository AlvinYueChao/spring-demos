package org.example.alvin.springexamples.annotation.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
    logger.info("====== AspectAnnotation around ǰ��֪ͨ, ����ǿ����: {} ======", joinPoint.getSignature().getName());
    Object result = joinPoint.proceed();
    logger.info("====== AspectAnnotation around ����֪ͨ, ����ǿ����: {} ======", joinPoint.getSignature().getName());
    return result;
  }

  @Before("pc1()")
  private void before(JoinPoint joinPoint) {
    logger.info("====== AspectAnnotation before ֪ͨ, ����ǿ����: {} ======", joinPoint.getSignature().getName());
  }

  @After("pc1()")
  private void after(JoinPoint joinPoint) {
    logger.info("====== AspectAnnotation after ֪ͨ, ����ǿ����: {} ======", joinPoint.getSignature().getName());
  }

  @AfterReturning("pc1()")
  private void afterReturning(JoinPoint joinPoint) {
    logger.info("====== AspectAnnotation afterReturning ֪ͨ, ����ǿ����: {} ======", joinPoint.getSignature().getName());
  }

  @AfterThrowing("pc1()")
  private void afterThrowing(JoinPoint joinPoint) {
    logger.info("====== AspectAnnotation after afterThrowing, ����ǿ����: {} ======", joinPoint.getSignature().getName());
  }
}
