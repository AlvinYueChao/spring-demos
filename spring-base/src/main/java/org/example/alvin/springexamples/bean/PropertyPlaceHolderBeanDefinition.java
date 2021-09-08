package org.example.alvin.springexamples.bean;

import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class PropertyPlaceHolderBeanDefinition implements BeanDefinitionRegistryPostProcessor {

  private final Logger logger = LogManager.getLogger(PropertyPlaceHolderBeanDefinition.class);

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    // 配置文件 + 占位符 实例化 bean
    logger.info("Start initializing custom bean...");
    try {
      Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties", ClassUtils.getDefaultClassLoader());
      String placeHolderBeanClasses = properties.getProperty("placeHolderBeanClasses");
      String[] classNames = placeHolderBeanClasses.split(",");
      for (String className : classNames) {
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(className);
        String beanName = BeanDefinitionReaderUtils.generateBeanName(beanDefinition, registry);
        registry.registerBeanDefinition(beanName, beanDefinition);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

  }
}
