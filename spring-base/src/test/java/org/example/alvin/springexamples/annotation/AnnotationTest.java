package org.example.alvin.springexamples.annotation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.alvin.springexamples.annotation.AnnotationBean.InnerBean;
import org.example.alvin.springexamples.annotation.AnnotationBean.InnerBeanFactory;
import org.example.alvin.springexamples.annotation.aop.cglib.CglibBeanFactory;
import org.example.alvin.springexamples.annotation.aop.cglib.UserService;
import org.example.alvin.springexamples.annotation.aop.proxy.MyProxy;
import org.example.alvin.springexamples.annotation.aop.proxy.Parent;
import org.example.alvin.springexamples.annotation.aop.proxy.People;
import org.example.alvin.springexamples.annotation.aop.proxy.XiaoMing;
import org.example.alvin.springexamples.annotation.aop.transaction.service.ServiceC;
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
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.ClassUtils;

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

  @Test
  void test12() throws Throwable {
    People people = (People) MyProxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), new Class<?>[]{People.class}, new Parent(new XiaoMing()));
    Objects.requireNonNull(people).findMM();
  }

  @Test
  void test13() throws SQLException {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    ServiceC beanC = applicationContext.getBean(ServiceC.class);
    /*
    在事务方法中手动进行 try-cache 的情况：
    1. 在 beanC 的事务方法中进行 try-cache：事务最终还是会 rollback
    根本原因: beanC, beanB, beanA 事务方法持有的是同一个 connectionHolder，在 beanB 事务切面处理异常时, 将 connectionHolder 的 rollbackOnly 属性设置为了 true
              所以在 beanC 的事务切面判断是否需要进行全局回滚时，结果为 true，从而进行了全局回滚
    2. 在 beanB 的事务方法中进行 try-cache：事务最终会提交
    根本原因：beanB 事务切面中并未对 rollbackOnly 设置 true，所以 beanC 的事务切面认为事务方法执行正常，进行了事务提交
     */
    beanC.doSomethingOneForC();
  }
}
