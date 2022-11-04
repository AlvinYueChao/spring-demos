package com.alvin.mybatis.spring;

import com.alvin.mapper.UserMapper;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Slf4j
public class MyBatisFactoryBean implements FactoryBean<Object> {

  private final Class<?> mapperInterface;

  public MyBatisFactoryBean(Class<?> mapperInterface) {
    this.mapperInterface = mapperInterface;
  }

  @Override
  public Object getObject() throws Exception {
    return Proxy.newProxyInstance(MyBatisFactoryBean.class.getClassLoader(), new Class[]{this.mapperInterface}, (proxy, method, args) -> {
      log.info("{}", method.getName());
      return null;
    });
  }

  @Override
  public Class<?> getObjectType() {
    return this.mapperInterface;
  }
}
