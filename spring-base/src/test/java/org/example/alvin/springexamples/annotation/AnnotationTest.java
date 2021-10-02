package org.example.alvin.springexamples.annotation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.alvin.springexamples.annotation.AnnotationBean.InnerBean;
import org.example.alvin.springexamples.annotation.AnnotationBean.InnerBeanFactory;
import org.example.alvin.springexamples.annotation.aop.cglib.CglibBeanFactory;
import org.example.alvin.springexamples.annotation.aop.cglib.UserService;
import org.example.alvin.springexamples.annotation.condition.BeansConditionalBean;
import org.example.alvin.springexamples.annotation.condition.ClassesConditionalBean;
import org.example.alvin.springexamples.annotation.condition.ConditionalBean;
import org.example.alvin.springexamples.annotation.condition.PropertiesConditionalBean;
import org.example.alvin.springexamples.annotation.deferredimport.SelectImportBean;
import org.example.alvin.springexamples.annotation.scanbean.ScanBean;
import org.example.alvin.springexamples.annotation.scanbean.mybasepackage.Teacher;
import org.example.alvin.springexamples.annotation.spi.service.AreaService;
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

  @Test
  void test5() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    /*
    ConditionalOnBean 失败原因：matches() 发生在 beanDefinition 注册之前
     */
    BeansConditionalBean bean = applicationContext.getBean(BeansConditionalBean.class);
    Assertions.assertNotNull(bean);
  }

  @Test
  void test6() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    Object bean1 = applicationContext.getBean("innerBean1");
    Object bean2 = applicationContext.getBean("innerBean2");
    Assertions.assertNotNull(bean1);
    Assertions.assertNotNull(bean2);
  }

  @Test
  void test7() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    Object bean1 = applicationContext.getBean("innerBean1");
    Object factoryBean = applicationContext.getBean("innerBeanFactory");
    int hashCode1 = bean1.hashCode();
    InnerBean innerBeanViaFactory = ((InnerBeanFactory) factoryBean).getInnerBean();
    int hashCode2 = innerBeanViaFactory.hashCode();
    // 外部类使用 @Component 时，同一 beanMethod 产生的两个 bean 的 hashCode 不相同，因为方法被调用了两次
//    Assertions.assertNotEquals(hashCode1, hashCode2);
    // 外部类使用 @Configuration 时，hashCode相同
    // @Configuration 中解析 @Bean 时，会使用切面，因此都会从 beanFactory 中获取 bean，所以两次调用得到的 bean 是同一个
    Assertions.assertEquals(hashCode1, hashCode2);
  }

  @Test
  void test8() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    PropertiesConditionalBean bean = applicationContext.getBean(PropertiesConditionalBean.class);
    Assertions.assertNotNull(bean);
  }

  @Test
  void test9() {
    UserService userService = (UserService) CglibBeanFactory.getInstance();
    String result = userService.doSomething0("Alvin");
    logger.info("result: {}", result);
  }

  @Test
  void test10() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
    Teacher bean = applicationContext.getBean(Teacher.class);
    Assertions.assertNotNull(bean);
  }

  @Test
  void test11() {
//    String basePackage = "org.example.alvin.springexamples.annotation.spi";
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    AreaService areaServiceImpl = applicationContext.getBean(AreaService.class);
    areaServiceImpl.queryAreaFromDB();
  }
}
