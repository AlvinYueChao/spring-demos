package org.example.alvin.springexamples.annotation.deferredimport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ImportBeanDefinitionRegistrarDemo implements ImportBeanDefinitionRegistrar {

  private final Logger logger = LogManager.getLogger(ImportBeanDefinitionRegistrarDemo.class);

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    // 通过 AnnotationMetadata 自定义 BeanDefinition 然后注册到 BeanDefinitionRegistry 中
    logger.info("Got into ImportBeanDefinitionRegistrarDemo.registerBeanDefinitions()");
  }
}
