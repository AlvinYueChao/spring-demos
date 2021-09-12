package org.example.alvin.springexamples.xml.designpattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDesignPattern {

  @Test
  void test2() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    SayHiClass sayHiClass = applicationContext.getBean(SayHiClass.class);
    Assertions.assertEquals("Hi, I'm a woman", sayHiClass.sayHi());
  }
}
