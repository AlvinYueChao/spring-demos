package org.example.alvin.springexamples.xml.bean;

import javax.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class BeanA implements DisposableBean {

  private final Logger logger = LogManager.getLogger(BeanA.class);

  @PreDestroy
  public void preDestroy() {
    logger.info("====== triggered function with @PreDestroy ======");
  }

  @Override
  public void destroy() {
    logger.info("====== triggered override destroy() ======");
  }
}
