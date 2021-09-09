package org.example.alvin.springexamples.bean.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class AnnotationFactoryBean implements FactoryBean<CustomBean> {

  @Override
  public CustomBean getObject() {
    return new CustomBean("test");
  }

  @Override
  public Class<?> getObjectType() {
    return CustomBean.class;
  }
}
