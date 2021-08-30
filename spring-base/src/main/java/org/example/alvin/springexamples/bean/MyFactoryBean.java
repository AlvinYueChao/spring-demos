package org.example.alvin.springexamples.bean;

public class MyFactoryBean {

  public CustomBean getObject() {
    return new CustomBean("Alvin");
  }
}
