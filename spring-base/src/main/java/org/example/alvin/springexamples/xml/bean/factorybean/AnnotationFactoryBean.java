package org.example.alvin.springexamples.xml.bean.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class AnnotationFactoryBean implements FactoryBean<CustomBeanForAnnotation> {

  /*
  触发条件：
  1. 当调用 getBean(AnnotationFactoryBean.class)时
  2. 当调用 getBean(CustomBean.class) 且 CustomBean 是单例对象
   */
  @Override
  public CustomBeanForAnnotation getObject() {
    return new CustomBeanForAnnotation("test");
  }

  @Override
  public Class<?> getObjectType() {
    return CustomBeanForAnnotation.class;
  }
}
