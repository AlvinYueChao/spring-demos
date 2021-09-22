package org.example.alvin.springexamples.annotation.aop.cglib;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class DoSomethingInterceptor2 implements MethodInterceptor {

  private final Logger logger = LogManager.getLogger(DoSomethingInterceptor2.class);

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    logger.info("{} ִ��ǰ...", method.getName());
    // ��������
    Object result = methodProxy.invokeSuper(o, objects);
    logger.info("{} ִ�к�...", method.getName());
    return result;
  }
}
