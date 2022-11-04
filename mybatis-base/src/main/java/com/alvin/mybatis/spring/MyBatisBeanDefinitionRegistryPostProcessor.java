package com.alvin.mybatis.spring;

import com.alvin.mapper.OrderMapper;
import com.alvin.mapper.UserMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

// test2()
//@Component
public class MyBatisBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    // solution2: 借助BeanDefinitionRegistryPostProcessor注册BeanDefinition的功能将MyBatisFactoryBean注册进来，从而将调用GetObject()对应生成的对象加入spring容器中
    AbstractBeanDefinition userMapperBd = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
    userMapperBd.setBeanClass(MyBatisFactoryBean.class);
    userMapperBd.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
    registry.registerBeanDefinition("userMapper", userMapperBd);

    AbstractBeanDefinition orderMapperBd = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
    orderMapperBd.setBeanClass(MyBatisFactoryBean.class);
    orderMapperBd.getConstructorArgumentValues().addGenericArgumentValue(OrderMapper.class);
    registry.registerBeanDefinition("orderMapper", orderMapperBd);
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

  }
}
