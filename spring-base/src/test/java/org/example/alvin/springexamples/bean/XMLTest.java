package org.example.alvin.springexamples.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.alvin.springexamples.bean.applicationeventlistener.MyApplicationEvent;
import org.example.alvin.springexamples.bean.applicationeventlistener.MyApplicationListener;
import org.example.alvin.springexamples.bean.factorybean.AnnotationFactoryBean;
import org.example.alvin.springexamples.bean.factorybean.CustomBean;
import org.example.alvin.springexamples.bean.placeholderbean.PlaceHolderBean1;
import org.example.alvin.springexamples.bean.scope.CustomScopeBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySources;

class XMLTest {

  private final Logger logger = LogManager.getLogger(XMLTest.class);

  @Test
  void test1() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    Student student = applicationContext.getBean(Student.class);
    Assertions.assertEquals("Alvin", student.getName());
    CustomBean customBean = applicationContext.getBean(CustomBean.class);
    Assertions.assertEquals("test", customBean.getName());
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
    /*
     * if MyApplicationListener has @Component tag, then {@link MyApplicationListener#onApplicationEvent(MyApplicationEvent)} will be triggered twice
     * Observe Pattern
     * {@link ApplicationEventMulticaster#addApplicationListener(org.springframework.context.ApplicationListener)}
     * 1. @Component => register the subscriber during the spring container startup
     * 2. addApplicationListener => register the subscriber after the spring container startup completely
     */
    applicationContext.addApplicationListener(new MyApplicationListener());
    applicationContext.publishEvent(new MyApplicationEvent("Alvin"));
  }

  @Test
  void test4() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    CustomBean bean = applicationContext.getBean(CustomBean.class);
    Assertions.assertEquals("Alvin", bean.getName());
  }

  @Test
  void test5() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
    BeanA beanBeforeDestroy = beanFactory.getBean(BeanA.class);
    beanFactory.destroyBean(beanBeforeDestroy);
    Student student = beanFactory.getBean(Student.class);
    beanFactory.destroyBean(student);
    /*
    �����ر�ʱ���ᴥ��destroy����������˳��:
    1. @PreDestroy ����ע�ķ���
    2. ʵ���� DisposableBean �ӿڵ� bean �� destroy()����
    3. �� <bean/> �������� destroy-method ����
     */
    beanFactory.destroySingletons();
  }

  @Test
  void test6() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    PropertiesBean bean = applicationContext.getBean(PropertiesBean.class);
    Assertions.assertEquals("test", bean.getName());
    Assertions.assertEquals("123456", bean.getPassword());
  }

  @Test
  void test7() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    PropertySourcesPlaceholderConfigurer bean = applicationContext.getBean(PropertySourcesPlaceholderConfigurer.class);
    // Ĭ�����Լ������ȼ�: ��������(ϵͳ����) > ���������ļ�
    PropertySources propertySources = bean.getAppliedPropertySources();
    logger.info("Properties: {}", propertySources);
  }

  @Test
  void test8() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    PlaceHolderBean1 bean = applicationContext.getBean(PlaceHolderBean1.class);
    Assertions.assertNotNull(bean);
  }

  @Test
  void test9() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    Object bean = applicationContext.getBean("annotationFactoryBean");
    Assertions.assertTrue(bean instanceof CustomBean);
    Object bean1 = applicationContext.getBean("&annotationFactoryBean");
    Assertions.assertTrue(bean1 instanceof AnnotationFactoryBean);
  }

  @Test
  void test10() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    applicationContext.getBeanFactory().registerSingleton("student", new Student());
    Student student = applicationContext.getBean(Student.class);
    Assertions.assertNotNull(student);
  }

  @Test
  void test11() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    CustomScopeBean bean = applicationContext.getBean(CustomScopeBean.class);
    Assertions.assertNotNull(bean);
  }
}