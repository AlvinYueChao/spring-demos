package com.alvin;

import com.alvin.mapper.OrderMapper;
import com.alvin.mapper.UserMapper;
import com.alvin.mybatis.spring.MyBatisFactoryBean;
import com.alvin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class MybatisSpringTest {

  @Test
  void test1() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AppConfig.class);

    // 手动注册FactoryBean并将生成的UserMapper, OrderMapper代理对象加入spring容器中
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
}
