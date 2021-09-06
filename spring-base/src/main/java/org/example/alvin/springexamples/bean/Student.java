package org.example.alvin.springexamples.bean;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
public class Student {

  private final Logger logger = LogManager.getLogger(Student.class);

  private String name = "Alvin";

  public void destroyMethod() {
    logger.info("====== triggered function mapped in destroy-method configuration ======");
  }
}
