package org.example.alvin.springexamples.annotation.aop.cglib;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.CallbackFilter;

public class CglibCallbackFilter implements CallbackFilter {

  @Override
  public int accept(Method method) {
    int result;
    if ("doSomething0".equalsIgnoreCase(method.getName())) {
      result = 0;
    } else if ("doSomething1".equalsIgnoreCase(method.getName())) {
      result = 1;
    } else if ("doSomething2".equalsIgnoreCase(method.getName())) {
      result = 2;
    } else if ("noOperation".equalsIgnoreCase(method.getName())) {
      result = 3;
    } else {
      result = 4;
    }
    return result;
  }
}
