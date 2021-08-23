package org.example.alvin.springexamples.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class TestBean {

  private static final Logger LOGGER = LogManager.getLogger(TestBean.class);

  @Test
  void test1() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    Student student = applicationContext.getBean(Student.class);
    Assertions.assertEquals("alvin", student.getName());
  }

  @Test
  void test2() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    BeanWithoutComponent bean = applicationContext.getBean(BeanWithoutComponent.class);
    Assertions.assertEquals("overrideName", bean.getName());
  }
}
