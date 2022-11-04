package com.alvin.mybatis.spring;

import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

public class MyBatisBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

  public MyBatisBeanDefinitionScanner(BeanDefinitionRegistry registry) {
    super(registry);
  }

  @Override
  protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
    return beanDefinition.getMetadata().isInterface();
  }

  @Override
  protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
    Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

    beanDefinitionHolders.forEach(x -> {
      BeanDefinition bd = x.getBeanDefinition();
      String mapperClassName = bd.getBeanClassName();
      assert mapperClassName != null;
      bd.getConstructorArgumentValues().addGenericArgumentValue(mapperClassName);
      bd.setBeanClassName(MyBatisFactoryBean.class.getName());
    });

    return beanDefinitionHolders;
  }

  @Override
  protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
//    return super.isCandidateComponent(metadataReader);
    return true;
  }
}
