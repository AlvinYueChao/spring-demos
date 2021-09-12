package org.example.alvin.springexamples.xml.designpattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Woman implements People {

  private final Logger logger = LogManager.getLogger(Woman.class);

  @Override
  public String sayHi() {
    return "Hi, I'm a woman";
  }
}
