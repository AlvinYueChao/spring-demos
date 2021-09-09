package org.example.alvin.springexamples.bean.factorybean;

public class MyFactoryBean {

  public CustomBean getObject() {
    return new CustomBean("Alvin");
  }
}
