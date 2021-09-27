package org.example.alvin.springexamples.annotation.spi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl2 implements AccountService {

  private final Logger logger = LogManager.getLogger(AccountServiceImpl2.class);

  @Override
  public void queryAccount() {
    logger.info("invoked AccountServiceImpl2::queryAccount");
  }
}
