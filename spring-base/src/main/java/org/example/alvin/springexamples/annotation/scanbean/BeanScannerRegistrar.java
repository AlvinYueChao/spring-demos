package org.example.alvin.springexamples.annotation.scanbean;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class BeanScannerRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    boolean acceptAllBeans = true;
    AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(BeanScanner.class.getName()));
    BeanPackageScanner scanner = new BeanPackageScanner(registry);
    Class<? extends Annotation> annotationClass = Objects.requireNonNull(annotationAttributes).getClass("annotationClass");
    if (!Annotation.class.equals(annotationClass)) {
      acceptAllBeans = false;
      scanner.addIncludeFilter(new AnnotationTypeFilter(annotationClass));
    }

    List<String> basePackages = new ArrayList<>();
    for (String packageName : annotationAttributes.getStringArray("value")) {
      if (!StringUtils.isBlank(packageName)) {
        basePackages.add(packageName);
      }
    }
    for (String packageName : annotationAttributes.getStringArray("basePackages")) {
      if (!StringUtils.isBlank(packageName)) {
        basePackages.add(packageName);
      }
    }

    if (acceptAllBeans) {
      // 将扫描到的所有的类全都加载到 Spring 容器中
      scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
    }
    scanner.doScan(basePackages.toArray(new String[0]));
  }
}
