package org.example.alvin.springexamples.xml.bean.factorybean;

public class MyFactoryBean {

  public CustomBeanForXml getObject() {
    return new CustomBeanForXml("Alvin");
  }
}
