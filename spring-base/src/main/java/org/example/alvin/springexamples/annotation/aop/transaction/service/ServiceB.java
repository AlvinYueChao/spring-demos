package org.example.alvin.springexamples.annotation.aop.transaction.service;

import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceB {

  private final Logger logger = LogManager.getLogger(ServiceB.class);

  private final DataSource dataSource;

  @Autowired
  public ServiceB(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Transactional
  public void doSomethingOneForB() {
    logger.info("====== using {} doSomethingForB ======", this.dataSource);
  }
}
