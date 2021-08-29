package org.example.alvin.springexamples.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class TestBean {

  private static final Logger LOGGER = LogManager.getLogger(TestBean.class);

  @Test
  void test1() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    Student student = applicationContext.getBean(Student.class);
    Assertions.assertEquals("Alvin", student.getName());
  }

  @Test
  void test2() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    BeanWithoutComponent bean = applicationContext.getBean(BeanWithoutComponent.class);
    Assertions.assertEquals("overrideName", bean.getName());
  }

  @Test
  void test3() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    /**
     * 如果给MyApplicationListener加上@Component注解，则{@link MyApplicationListener#onApplicationEvent(MyApplicationEvent)}会被执行两次
     * 观察者模式
     * {@link ApplicationEventMulticaster#addApplicationListener(org.springframework.context.ApplicationListener)}
     */
//    applicationContext.addApplicationListener(new MyApplicationListener());
    applicationContext.publishEvent(new MyApplicationEvent("Alvin"));
    applicationContext.start();
  }
}
