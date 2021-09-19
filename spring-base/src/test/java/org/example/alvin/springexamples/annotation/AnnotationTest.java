package org.example.alvin.springexamples.annotation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.alvin.springexamples.annotation.condition.ClassesConditionalBean;
import org.example.alvin.springexamples.annotation.condition.ConditionalBean;
import org.example.alvin.springexamples.annotation.deferredimport.SelectImportBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AnnotationTest {

  private final Logger logger = LogManager.getLogger(AnnotationTest.class);

  private static final String BASE_PACKAGE = "org.example.alvin.springexamples.annotation";

  @Test
  void test1() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    PropertySourceBean bean = applicationContext.getBean(PropertySourceBean.class);
    Assertions.assertEquals("test", bean.getName());
    Assertions.assertEquals("123456", bean.getPassword());
  }

  @Test
  void test2() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    SelectImportBean bean = applicationContext.getBean(SelectImportBean.class);
    Assertions.assertNotNull(bean);
  }

  @Test
  void test3() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    // 当 matches() 返回为 false 时，@Component 所在的 bean 类型并未被注册到 spring 容器中，所以 getBean() 会报错
    Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(ConditionalBean.class));
//    ConditionalBean beanWithMatchedCondition = applicationContext.getBean(ConditionalBean.class);
//    Assertions.assertNotNull(beanWithMatchedCondition);
  }

  @Test
  void test4() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    ClassesConditionalBean bean = applicationContext.getBean(ClassesConditionalBean.class);
    Assertions.assertNotNull(bean);
  }
}
