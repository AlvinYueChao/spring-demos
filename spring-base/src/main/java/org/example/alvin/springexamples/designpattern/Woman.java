package org.example.alvin.springexamples.designpattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Woman implements People {

  private static final Logger LOGGER = LogManager.getLogger(Woman.class);

  @Override
  public String sayHi() {
    return "Hi, I'm a woman";
  }
}
