package org.example.alvin.springexamples.annotation.aop.transaction.service;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceC {

  private final Logger logger = LogManager.getLogger(ServiceC.class);

  private final ServiceA serviceA;
  private final ServiceB serviceB;

  @Autowired
  public ServiceC(ServiceA serviceA, ServiceB serviceB) {
    this.serviceA = serviceA;
    this.serviceB = serviceB;
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
  public void doSomethingOneForC() throws SQLException {
    try {
      logger.info("====== using {} doSomethingForC ======", this.serviceA);
      this.serviceA.doSomethingOneForA();
      logger.info("====== using {} doSomethingForC ======", this.serviceB);
      this.serviceB.doSomethingOneForB();
    } catch (RuntimeException e) {
      logger.warn("cached runtime exception", e);
    }

    /*logger.info("====== using {} doSomethingForC ======", this.serviceA);
    this.serviceA.doSomethingOneForA();
    logger.info("====== using {} doSomethingForC ======", this.serviceB);
    this.serviceB.doSomethingOneForB();*/
  }
}
