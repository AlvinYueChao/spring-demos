package org.example.alvin.springexamples.annotation.spi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import org.example.alvin.springexamples.annotation.spi.handler.InvokeHandler;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.ClassUtils;

public class InvokeProxy {

  public static Object newProxy(Field field, ApplicationContext context) {
    return Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), new Class[]{field.getType()}, new InvokeAdvice(field, context));
  }

  private static class InvokeAdvice implements InvocationHandler {

    private final Field field;
    private final ApplicationContext context;

    public InvokeAdvice(Field field, ApplicationContext context) {
      this.field = field;
      this.context = context;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
      Map<String, InvokeHandler> beansOfType = context.getBeansOfType(InvokeHandler.class);
      AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(field, DI.class);
      String value = Objects.requireNonNull(attributes).getString("value");
      String[] serviceIds = attributes.getStringArray("serviceIds");
      for (Entry<String, InvokeHandler> entry : beansOfType.entrySet()) {
        if (entry.getValue().support(value)) {
          entry.getValue().invoke(proxy, method, args, field, serviceIds);
        }
      }
      return null;
    }
  }
}
