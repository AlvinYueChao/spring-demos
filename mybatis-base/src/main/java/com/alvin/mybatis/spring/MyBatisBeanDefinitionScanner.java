package com.alvin.mybatis.spring;

import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
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
      GenericBeanDefinition bd = (GenericBeanDefinition) x.getBeanDefinition();
      String mapperClassName = bd.getBeanClassName();
      assert mapperClassName != null;
      bd.getConstructorArgumentValues().addGenericArgumentValue(mapperClassName);
      // AUTOWIRE_BY_TYPE 会自动从BeanClass中找出set方法，然后根据类型从spring容器中匹配对应的Bean
      bd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
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
