package org.example.alvin.springexamples.xml.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ValueBean implements EnvironmentAware {

  private final Logger logger = LogManager.getLogger(ValueBean.class);

  @Value("${propertiesBean.name}")
  private String name;

  @Override
  public void setEnvironment(Environment environment) {
    /*
    @Value 属性来源：environment，本地配置文件
     */
    logger.info("value of name: {}", name);
    logger.info("value of propertiesBean.name property: {}", environment.getProperty("propertiesBean.name"));
  }
}
