package org.example.alvin.mybatisexamples.plugin;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

public class PagePlugin implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    return null;
  }
}
