package com.alvin.mybatis.spring;

import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyBatisImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    Map<String, Object> myBatisMapperScanAnno = importingClassMetadata.getAnnotationAttributes(MyBatisMapperScan.class.getName());
    String path = (String) Objects.requireNonNull(myBatisMapperScanAnno).get("value");
    MyBatisBeanDefinitionScanner myBatisBeanDefinitionScanner = new MyBatisBeanDefinitionScanner(registry);
    myBatisBeanDefinitionScanner.scan(path);
  }
}
