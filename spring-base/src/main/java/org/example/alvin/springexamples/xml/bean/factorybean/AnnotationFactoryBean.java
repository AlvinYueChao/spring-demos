package org.example.alvin.springexamples.xml.bean.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class AnnotationFactoryBean implements FactoryBean<CustomBean> {

  /*
  ����������
  1. ������ getBean(AnnotationFactoryBean.class)ʱ
  2. ������ getBean(CustomBean.class) �� CustomBean �ǵ�������
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
