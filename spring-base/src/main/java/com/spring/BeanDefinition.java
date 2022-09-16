package com.spring;

import lombok.Data;

@Data
public class BeanDefinition {

  private Class<?> type;
  private String scope;
  private boolean isLazy;
}
