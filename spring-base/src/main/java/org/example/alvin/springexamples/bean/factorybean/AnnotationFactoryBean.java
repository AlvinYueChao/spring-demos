package org.example.alvin.springexamples.bean.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class AnnotationFactoryBean implements FactoryBean<CustomBean> {

  /*
  触发条件：
  1. 当调用 getBean(AnnotationFactoryBean.class)时
  2. 当调用 getBean(CustomBean.class) 且 CustomBean 是单例对象
   */
  // 只有当调用 getBean(AnnotationFactoryBean.class) 时，才会真正触发该方法
  @Override
  public CustomBean getObject() {
    return new CustomBean("test");
  }

  @Override
  public Class<?> getObjectType() {
    return CustomBean.class;
  }
}
