package org.example.alvin.springexamples.xml.bean.circularref;

import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularRefA {

  private final Logger logger = LogManager.getLogger(CircularRefA.class);

  // 属性依赖注入是允许的
  @Autowired
  private CircularRefB circularRefB;

  public CircularRefA() {
    logger.info("============== CircularRefA() ============");
  }

  @PostConstruct
  public void postConstruct() {
    logger.info("Dependency injection completed successfully. {}", this);
  }
}
