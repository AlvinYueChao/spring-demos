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
    propagation = Propagation.REQUIRED
    在事务方法中手动进行 try-cache 的情况：
    1. 在 serviceC 的事务方法中进行 try-cache：事务最终会 rollback
    根本原因: serviceC, serviceB, serviceA 事务方法持有的是同一个 connectionHolder，在 beanB 事务切面处理异常时, 将 connectionHolder 的 rollbackOnly 属性设置为了 true
              所以在 serviceC 的事务切面判断是否需要进行全局回滚时，结果为 true，从而进行了全局回滚
    2. 在 serviceB 的事务方法中进行 try-cache：事务最终会提交
    根本原因：serviceB 事务切面中并未对 rollbackOnly 设置 true，所以 serviceC 的事务切面认为事务方法执行正常，进行了事务提交
    propagation = Propagation.NESTED
    1. 不进行任何手动的 try-cache：事务最终会 rollback
    根本原因：serviceA 正常提交之后清除了回滚点，serviceB 事务切面回滚到了当前事务切面开始时设置的回滚点并抛出异常，serviceC 事务切面捕获异常之后进行无回滚点的回滚，因为三个事务切面持有
              的连接是同一个，所以 serviceA 的事务也被全部回滚
    2. 在 serviceC 的事务方法中进行 try-cache：事务最终会部分提交
    根本原因：serviceB 的事务切面捕获了异常，但是由于 serviceB 的事务传播属性是 NESTED，所以 serviceB 的事务切面没有设置 rollbackOnly，而是在设置之前就进入了 NESTED 对应的 rollback 逻辑
              中，在这个逻辑中进行了事务的回滚，所以 serviceB 的数据库操作被回滚而且此时的 rollbackOnly 的值仍为 false，所以 serviceC 的事务切面进行提交的时候仅有 serviceA 的数据库操作生效，
              所以只有 tablea 插入了一条数据
    3. 在 serviceB 的事务方法中进行 try-cache：事务最终会全部提交
    根本原因：异常从第一次抛出就被吞掉，事务切面没有捕获任何一场，认为执行正常所以进行最终提交

    如果将编程式事务嵌套在 @Transaction 方法中，有以下几点需要注意：
    1) 编程式事务中，连接对象和当前 @Transaction 方法中持有的连接对象是同一个
    2) 编程式事务相当于在现有 @Transaction 事务切面中嵌套了一个 @Transaction(propagation = Propagation.REQUIRED) 的事务切面
    3) 编程式事务中抛出 RuntimeTimeException和 Error 及其子类，则编程式事务和当前 Spring 事务切面均进行回滚。因为编程式事务捕获异常并进行 rollback 操作后，会向外抛出该异常
     */
    beanC.doSomethingOneForC();
  }
}
