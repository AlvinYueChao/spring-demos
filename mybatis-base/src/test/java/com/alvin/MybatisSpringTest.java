package com.alvin;

import com.alvin.mapper.OrderMapper;
import com.alvin.mapper.UserMapper;
import com.alvin.mybatis.spring.MyBatisFactoryBean;
import com.alvin.service.UserService;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
class MybatisSpringTest {

  @Test
  void test0() throws IOException {
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class); // 代理对象
    String result = mapper.selectUserById();

    sqlSession.commit();
    sqlSession.flushStatements();
    sqlSession.close();
  }

  @Test
  void test1() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AppConfig.class);

    // solution1: 手动注册FactoryBean并将生成的UserMapper, OrderMapper代理对象加入spring容器中
    AbstractBeanDefinition userMapperBd = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
    userMapperBd.setBeanClass(MyBatisFactoryBean.class);
    userMapperBd.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
    context.registerBeanDefinition("userMapper", userMapperBd);

    AbstractBeanDefinition orderMapperBd = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
    orderMapperBd.setBeanClass(MyBatisFactoryBean.class);
    orderMapperBd.getConstructorArgumentValues().addGenericArgumentValue(OrderMapper.class);
    context.registerBeanDefinition("orderMapper", orderMapperBd);

    context.refresh();

    UserService userService = (UserService) context.getBean("userService");
    userService.test();
  }

  @Test
  void test2() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AppConfig.class);
    context.refresh();

    UserService userService = (UserService) context.getBean("userService");
    userService.test();
  }

  @Test
  void test3() {

  }
}
