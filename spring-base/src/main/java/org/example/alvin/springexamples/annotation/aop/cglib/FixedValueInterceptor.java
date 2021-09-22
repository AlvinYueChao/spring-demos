package org.example.alvin.springexamples.annotation.aop.cglib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cglib.proxy.FixedValue;

public class FixedValueInterceptor implements FixedValue {

  private final Logger logger = LogManager.getLogger(FixedValueInterceptor.class);

  @Override
  public Object loadObject() throws Exception {
    logger.info("====== loadObject value ======");
    return "my fixed value";
  }
}
