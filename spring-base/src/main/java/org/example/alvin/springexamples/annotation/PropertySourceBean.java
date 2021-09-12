package org.example.alvin.springexamples.annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(name = "local", value = "application.properties")
public class PropertySourceBean {

  @Value("${propertiesBean.name}")
  private String name;
  @Value("${propertiesBean.password}")
  private String password;
}
