package org.example.alvin.springexamples.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AnnotationTest {

  private final Logger logger = LogManager.getLogger(AnnotationTest.class);

  private static final String BASE_PACKAGE = "org.example.alvin.springexamples.annotation";

  @Test
  void test1() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);

  }
}
