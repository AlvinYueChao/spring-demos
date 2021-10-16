package org.example.alvin.springexamples.annotation;

import javax.annotation.PostConstruct;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(name = "local", value = "application.properties")
public class PropertySourceBean {

  private final Logger logger = LogManager.getLogger(PropertySourceBean.class);

  @Value("${propertiesBean.name}")
  private String name;
  @Value("${propertiesBean.password}")
  private String password;

  @PostConstruct
  private void showMembers() {
    logger.info("name: {}, password: {}", this.name, this.password);
  }
}
