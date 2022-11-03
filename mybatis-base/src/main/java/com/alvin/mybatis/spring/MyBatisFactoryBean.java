package com.alvin.mybatis.spring;

import com.alvin.mapper.UserMapper;
import java.lang.reflect.Proxy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyBatisFactoryBean implements FactoryBean<Object> {

  @Override
  public Object getObject() throws Exception {
    Object proxyInstance = Proxy.newProxyInstance(MyBatisFactoryBean.class.getClassLoader(), new Class[]{UserMapper.class}, (proxy, method, args) -> null);
    return proxyInstance;
  }

  @Override
  public Class<?> getObjectType() {
    return UserMapper.class;
  }
}
