package org.example.alvin.springexamples.annotation.condition;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class BeansCondition implements Condition {

  private final Logger logger = LogManager.getLogger(BeansCondition.class);

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    boolean isMatched = false;
    if (metadata.isAnnotated(ConditionalOnBean.class.getName())) {
      MergedAnnotations annotations = metadata.getAnnotations();
      MergedAnnotation<ConditionalOnBean> cob = annotations.get(ConditionalOnBean.class);
      Optional<Object> valueOpt = cob.getValue("value");
      ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
      if (valueOpt.isPresent() && beanFactory != null) {
        Class<?>[] classes = (Class<?>[]) valueOpt.get();
        for (Class<?> aClass : classes) {
          String[] beanNamesForType = beanFactory.getBeanNamesForType(aClass, true, true);
          isMatched = beanNamesForType.length > 0;
        }
      }
    }
    return isMatched;
  }
}
