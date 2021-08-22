package org.example.alvin.springexamples.designpattern.delegate;

public abstract class SayHiClass {

  public String sayHi() {
    return getPeople().sayHi();
  }

  public abstract People getPeople();
}
