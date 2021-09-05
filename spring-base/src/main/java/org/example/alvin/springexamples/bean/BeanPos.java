package org.example.alvin.springexamples.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class BeanPos implements BeanDefinitionRegistryPostProcessor {

  private final Logger logger = LogManager.getLogger(BeanPos.class);

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    logger.info("Invoked into postProcessBeanDefinitionRegistry, params: {}", registry);

    // 增删改查 beanDefinition
    GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
    genericBeanDefinition.setBeanClass(BeanWithoutComponent.class);
    MutablePropertyValues propertyValues = genericBeanDefinition.getPropertyValues();
    propertyValues.add("name", "overrideName");
    registry.registerBeanDefinition("beanWithoutComponent", genericBeanDefinition);

    String[] beanDefinitionNames = registry.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
      logger.info("BeanDefinition: {}", beanDefinition);
    }
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    logger.info("Invoked into postProcessBeanFactory, params: {}", beanFactory);
  }
}
