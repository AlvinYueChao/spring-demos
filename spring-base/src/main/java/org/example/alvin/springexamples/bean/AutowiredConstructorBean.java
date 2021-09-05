package org.example.alvin.springexamples.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowiredConstructorBean {
  private final Logger logger = LogManager.getLogger(AutowiredConstructorBean.class);

  @Autowired
  public AutowiredConstructorBean(BeanA beanA, BeanB beanB) {
    logger.info("Parameters: {}, {}", beanA, beanB);
  }
}
