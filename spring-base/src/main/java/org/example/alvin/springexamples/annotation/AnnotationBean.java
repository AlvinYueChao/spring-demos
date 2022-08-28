package org.example.alvin.springexamples.annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
@Configuration
public class AnnotationBean {

  @Qualifier("innerBean1")
  @Bean()
  public InnerBean innerBean1() {
    return new InnerBean();
  }

  @Qualifier("innerBean2")
  @Bean
  public InnerBean innerBean2() {
    return new InnerBean();
  }

  @Bean
  public InnerBeanFactory innerBeanFactory() {
    InnerBeanFactory factory = new InnerBeanFactory();
    factory.setInnerBean(innerBean1());
    return factory;
  }

  public static class InnerBean {

  }

  @Data
  public static class InnerBeanFactory {

    private InnerBean innerBean;
  }
}
