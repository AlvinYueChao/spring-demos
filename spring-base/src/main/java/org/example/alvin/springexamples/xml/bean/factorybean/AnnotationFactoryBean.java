package org.example.alvin.springexamples.xml.bean.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class AnnotationFactoryBean implements FactoryBean<CustomBean> {

  /*
  触发条件：
  1. 当调用 getBean(AnnotationFactoryBean.class)时
  2. 当调用 getBean(CustomBean.class) 且 CustomBean 是单例对象
   */
  @Override
  public CustomBean getObject() {
    return new CustomBean("test");
  }

  @Override
  public Class<?> getObjectType() {
    return CustomBean.class;
  }
}
