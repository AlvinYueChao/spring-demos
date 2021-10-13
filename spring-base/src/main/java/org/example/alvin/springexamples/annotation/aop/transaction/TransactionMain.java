package org.example.alvin.springexamples.annotation.aop.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class TransactionMain {

  private final Logger logger = LogManager.getLogger(TransactionMain.class);

  @Transactional
  private void transactionMethod1() {
    logger.info("====== invoked TransactionMain.transactionMethod1 ======");
  }
}
