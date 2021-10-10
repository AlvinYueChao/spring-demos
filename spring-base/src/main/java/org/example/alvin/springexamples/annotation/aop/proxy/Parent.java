package org.example.alvin.springexamples.annotation.aop.proxy;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Parent implements MyInvocationHandler{

  private final Logger logger = LogManager.getLogger(Parent.class);

  private final People people;

  public Parent(People people) {
    this.people = people;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    beforeAdvice();
    method.invoke(people, (Object[]) null);
    afterAdvice();
    return null;
  }

  private void beforeAdvice() {
    logger.info("小明的父母帮小明找对象");
  }

  private void afterAdvice() {
    logger.info("小明的父母帮小明找到对象后，继续帮小明操持婚礼");
  }
}
