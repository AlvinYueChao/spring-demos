package org.example.alvin.springexamples.annotation.aop.cglib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

  private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

  @Override
  public String doSomething0(String param) {
    logger.info("====== doSomething0 ======");
    return "doSomething0";
  }

  @Override
  public String doSomething1(String param) {
    logger.info("====== doSomething1 ======");
    return "doSomething1";
  }

  @Override
  public String doSomething2(String param) {
    logger.info("====== doSomething1 ======");
    return "doSomething1";
  }

  @Override
  public String myMethod(String param) {
    logger.info("====== myMethod ======");
    return "myMethod";
  }
}
