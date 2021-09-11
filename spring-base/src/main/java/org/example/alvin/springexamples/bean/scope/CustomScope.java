package org.example.alvin.springexamples.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class CustomScope implements Scope {

  private final ThreadLocal<Object> local = new ThreadLocal<>();

  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    Object o = local.get();
    if (o != null) {
      return o;
    }
    o = objectFactory.getObject();
    local.set(o);
    return o;
  }

  @Override
  public Object remove(String name) {
    Object o = local.get();
    local.remove();
    return o;
  }

  @Override
  public void registerDestructionCallback(String name, Runnable callback) {

  }

  @Override
  public Object resolveContextualObject(String key) {
    return null;
  }

  @Override
  public String getConversationId() {
    return null;
  }
}
