package org.example.alvin.springexamples.xml.bean.factorybean;

import lombok.Data;

@Data
public class CustomBeanForXml {

  private String name;

  public CustomBeanForXml(String name) {
    this.name = name;
  }
}
