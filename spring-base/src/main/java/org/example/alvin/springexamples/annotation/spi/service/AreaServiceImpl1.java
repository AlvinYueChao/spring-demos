package org.example.alvin.springexamples.annotation.spi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.alvin.springexamples.annotation.spi.DI;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl1 implements AreaService {

  private final Logger logger = LogManager.getLogger(AreaServiceImpl1.class);

  @DI(value = "all", serverIds = {"accountServiceImpl1", "accountServiceImpl2"})
  private AccountService accountService;

  @Override
  public void queryAreaFromDB() {
    logger.info("invoked AreaServiceImpl1::queryAreaFromDB");
    accountService.queryAccount();
  }
}
