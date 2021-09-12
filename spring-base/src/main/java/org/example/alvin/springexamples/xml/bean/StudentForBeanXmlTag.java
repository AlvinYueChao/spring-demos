package org.example.alvin.springexamples.xml.bean;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
public class StudentForBeanXmlTag {

  private final Logger logger = LogManager.getLogger(StudentForBeanXmlTag.class);

  private String name = "Alvin";

  public void destroyMethod() {
    logger.info("====== triggered function mapped in destroy-method configuration ======");
  }
}
