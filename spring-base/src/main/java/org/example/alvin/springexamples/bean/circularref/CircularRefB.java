package org.example.alvin.springexamples.bean.circularref;

import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularRefB {

  private final Logger logger = LogManager.getLogger(CircularRefB.class);

  @Autowired
  private CircularRefA circularRefA;

  public CircularRefB() {
    logger.info("============== CircularRefB() ============");
  }

  @PostConstruct
  public void postConstruct() {
    logger.info("Dependency injection completed successfully. {}", this);
  }
}
