package org.example.alvin.springexamples.annotation.spi;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AutoConfigurationImportSelector.class)
public class AutoConfig {

//  @Bean
  public DIAnnotationBeanPostProcessor diAnnotationBeanPostProcessor() {
    // 为了避免提前实例化 DIAnnotationBeanPostProcessor, 将它采用 import 的方式加入到 spring 容器中，避免对 DatabaseConfig 的 @Value 解析造成干扰
    return new DIAnnotationBeanPostProcessor();
  }
}
