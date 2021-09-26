package org.example.alvin.springexamples.annotation.scanbean;

import java.util.Set;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

public class BeanPackageScanner extends ClassPathBeanDefinitionScanner {

  public BeanPackageScanner(BeanDefinitionRegistry registry) {
    super(registry, false);
  }

  @Override
  protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
    return super.doScan(basePackages);
  }
}
