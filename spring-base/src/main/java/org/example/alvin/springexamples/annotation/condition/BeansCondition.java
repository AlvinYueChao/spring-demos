package org.example.alvin.springexamples.annotation.condition;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

public class BeansCondition implements Condition {

  private final Logger logger = LogManager.getLogger(BeansCondition.class);

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    boolean isMatched = false;
    if (metadata.isAnnotated(ConditionOnBeans.class.getName())) {
      MergedAnnotations annotations = metadata.getAnnotations();
      MergedAnnotation<ConditionOnBeans> cob = annotations.get(ConditionOnBeans.class);
      Optional<Object> valueOpt = cob.getValue("value");
      ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
      if (valueOpt.isPresent() && beanFactory != null) {
        Class<?>[] classes = (Class<?>[]) valueOpt.get();
        boolean isBrokeDueToNotMatch = false;
        for (Class<?> aClass : classes) {
          // potential bug: the bean is created by other ways instead of @Component
          Optional<Annotation> componentAnnotationOpt = Arrays.stream(aClass.getAnnotations()).filter(x -> x.annotationType().equals(Component.class)).findFirst();
          if (componentAnnotationOpt.isEmpty()) {
            isBrokeDueToNotMatch = true;
            break;
          }
        }
        isMatched = !isBrokeDueToNotMatch;
      }
    }
    return isMatched;
  }
}
