package org.example.alvin.springexamples.xml.bean.factorybean;

public class MyFactoryBean {

  public CustomBean getObject() {
    return new CustomBean("Alvin");
  }
}
