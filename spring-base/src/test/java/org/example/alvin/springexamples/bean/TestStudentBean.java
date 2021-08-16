package org.example.alvin.springexamples.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class TestStudentBean {

  private static final Logger LOGGER = LogManager.getLogger(TestStudentBean.class);

  @Test
  void test1() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    Student student = applicationContext.getBean(Student.class);
    LOGGER.info("Name of the student: {}", student.getName());
  }
}
