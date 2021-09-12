package org.example.alvin.springexamples.xml.bean.factorybean;

import lombok.Data;

@Data
public class CustomBeanForAnnotation {

  private String name;

  public CustomBeanForAnnotation(String name) {
    this.name = name;
  }
}
