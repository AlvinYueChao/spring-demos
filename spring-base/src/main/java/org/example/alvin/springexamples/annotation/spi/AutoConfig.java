package org.example.alvin.springexamples.annotation.spi;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AutoConfigurationImportSelector.class)
public class AutoConfig {

//  @Bean
  public DIAnnotationBeanPostProcessor diAnnotationBeanPostProcessor() {
    // Ϊ�˱�����ǰʵ���� DIAnnotationBeanPostProcessor, �������� import �ķ�ʽ���뵽 spring �����У������ DatabaseConfig �� @Value ������ɸ���
    return new DIAnnotationBeanPostProcessor();
  }
}
