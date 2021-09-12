package org.example.alvin.springexamples.xml.bean.factorybean;

import lombok.Data;

@Data
public class CustomBean {

  private String name;

  public CustomBean(String name) {
    this.name = name;
  }
}
