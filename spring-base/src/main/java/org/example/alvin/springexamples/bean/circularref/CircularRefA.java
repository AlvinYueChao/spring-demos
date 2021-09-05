package org.example.alvin.springexamples.bean.circularref;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularRefA {

  private final Logger logger = LogManager.getLogger(CircularRefA.class);

  @Autowired
  private CircularRefB circularRefB;

  public CircularRefA() {
    logger.info("============== CircularRefA() ============");
  }
}
