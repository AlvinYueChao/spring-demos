package org.example.alvin.springexamples.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowiredConstructorBean {
  private static final Logger LOGGER = LogManager.getLogger(AutowiredConstructorBean.class);

  @Autowired
  public AutowiredConstructorBean(BeanA beanA, BeanB beanB) {
    LOGGER.info("Parameters: {}, {}", beanA, beanB);
  }
}
