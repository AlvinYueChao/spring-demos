package org.example.alvin.springexamples.annotation.spi.handler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class InvokeAssignedHandlerImpl implements InvokeHandler, ApplicationContextAware {

  private final Logger logger = LogManager.getLogger(InvokeAssignedHandlerImpl.class);

  private ApplicationContext applicationContext;

  @Override
  public boolean support(String type) {
    return "Assigned".equalsIgnoreCase(type);
  }

  @Override
  public Object invoke(Object bean, Method method, Object[] args, Field field, String[] serviceIds) {
    for (String serviceId : serviceIds) {
      try {
        method.invoke(applicationContext.getBean(serviceId), args);
      } catch (IllegalAccessException | InvocationTargetException e) {
        logger.error("Cache exception when invoke the method", e);
      }
    }
    return null;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
