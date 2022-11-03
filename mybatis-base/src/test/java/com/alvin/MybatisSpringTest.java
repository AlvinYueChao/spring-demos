package com.alvin;

import com.alvin.mapper.UserMapper;
import com.alvin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class MybatisSpringTest {

  @Test
  void test1() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    /*
    //
    AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
    beanDefinition.setBeanClass(UserMapper.class);
    context.registerBeanDefinition("userMapper", beanDefinition);*/

    UserService userService = (UserService) context.getBean("userService");
    userService.test();
  }
}
