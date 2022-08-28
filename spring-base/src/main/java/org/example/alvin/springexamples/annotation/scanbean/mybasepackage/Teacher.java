package org.example.alvin.springexamples.annotation.scanbean.mybasepackage;

import lombok.Data;
import org.example.alvin.springexamples.annotation.scanbean.CustomComponent;

@Data
@CustomComponent
public class Teacher {

  private String name;
  private int age;
}
