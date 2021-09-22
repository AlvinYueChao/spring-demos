package org.example.alvin.springexamples.annotation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.alvin.springexamples.annotation.AnnotationBean.InnerBean;
import org.example.alvin.springexamples.annotation.AnnotationBean.InnerBeanFactory;
import org.example.alvin.springexamples.annotation.aop.cglib.CglibBeanFactory;
import org.example.alvin.springexamples.annotation.aop.cglib.UserService;
import org.example.alvin.springexamples.annotation.condition.BeanConditionalBean;
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
    // �� matches() ����Ϊ false ʱ��@Component ���ڵ� bean ���Ͳ�δ��ע�ᵽ spring �����У����� getBean() �ᱨ��
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
    ConditionalOnBean ʧ��ԭ��matches() ������ beanDefinition ע��֮ǰ
     */
    BeanConditionalBean bean = applicationContext.getBean(BeanConditionalBean.class);
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
    // �ⲿ��ʹ�� @Component ʱ��ͬһ beanMethod ���������� bean �� hashCode ����ͬ����Ϊ����������������
//    Assertions.assertNotEquals(hashCode1, hashCode2);
    // �ⲿ��ʹ�� @Configuration ʱ��hashCode��ͬ
    Assertions.assertEquals(hashCode1, hashCode2);
  }

  @Test
  void test8() {
    UserService userService = (UserService) CglibBeanFactory.getInstance();
    String result = userService.doSomething0("Alvin");
    logger.info("result: {}", result);
  }
}
