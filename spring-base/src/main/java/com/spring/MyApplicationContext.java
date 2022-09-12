package com.spring;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyApplicationContext {

  private final Class<?> configClass;

  public MyApplicationContext(Class<?> configClass) {
    this.configClass = configClass;

    // 扫描
    if (configClass.isAnnotationPresent(ComponentScan.class)) {
      ComponentScan componentScanAnnotation = configClass.getAnnotation(ComponentScan.class);
      String path = componentScanAnnotation.value();

    }
  }

  public Object getBean(String beanName) {
    //todo: implement
    return null;
  }
}
