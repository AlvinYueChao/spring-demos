package org.example.alvin.springexamples.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBean {

  @Qualifier("innerBean1")
  @Bean()
  public InnerBean innerBean1() {
    return new InnerBean();
  }

  @Qualifier("innerBean2")
  @Bean()
  public InnerBean innerBean2() {
    return new InnerBean();
  }

  private static class InnerBean {

  }
}
