package org.example.alvin.springexamples.annotation.aop.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XiaoMing implements People{

  private final Logger logger = LogManager.getLogger(XiaoMing.class);

  @Override
  public void findMM() {
    logger.info("小明没时间找对象");
  }
}
