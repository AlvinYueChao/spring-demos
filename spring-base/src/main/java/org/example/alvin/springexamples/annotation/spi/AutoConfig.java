package org.example.alvin.springexamples.annotation.spi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AutoConfigurationImportSelector.class)
public class AutoConfig {

  @Bean
  public DIAnnotationBeanPostProcessor diAnnotationBeanPostProcessor() {
    return new DIAnnotationBeanPostProcessor();
  }
}
