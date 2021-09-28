package org.example.alvin.springexamples.annotation.spi.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface InvokeHandler {

  boolean support(String type);

  Object invoke(Object bean, Method method, Object[] args, Field field, String[] serviceIds);
}
