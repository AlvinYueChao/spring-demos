package org.example.alvin.springexamples.bean.factorybean;

import lombok.Data;

@Data
public class CustomBean {

  private String name;

  public CustomBean(String name) {
    this.name = name;
  }
}
