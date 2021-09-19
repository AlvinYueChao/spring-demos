package org.example.alvin.springexamples.annotation.condition;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotation.Adapt;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class BeansCondition implements Condition {

  private final Logger logger = LogManager.getLogger(BeansCondition.class);

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    boolean isMatched = false;
    if (metadata.isAnnotated(ConditionalOnBean.class.getName())) {
      /*Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnBean.class.getName());
      Object classNames = Objects.requireNonNull(annotationAttributes).get("classNames");
      ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
      if (classNames instanceof String[] && beanFactory != null) {
        for (String className : (String[]) classNames) {
          try {
            String[] beanNamesForType = beanFactory.getBeanNamesForType(ClassUtils.forName(className, ClassUtils.getDefaultClassLoader()), true, false);
            isMatched = beanNamesForType.length > 0;
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        }
      }*/
      MergedAnnotations annotations = metadata.getAnnotations();
      MultiValueMap<String, Object> attributes = annotations.stream(ConditionalOnBean.class)
          .filter(MergedAnnotationPredicates.unique(MergedAnnotation::getMetaTypes))
          .collect(MergedAnnotationCollectors.toMultiValueMap(Adapt.CLASS_TO_STRING));
      MergedAnnotation<ConditionalOnBean> cob = annotations.get(ConditionalOnBean.class);
      Optional<Object> valueOpt = cob.getValue("value");
      ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
      if (valueOpt.isPresent() && beanFactory != null) {
        Class<?>[] classes = (Class<?>[]) valueOpt.get();
        for (Class<?> aClass : classes) {
          String[] beanNamesForType = beanFactory.getBeanNamesForType(aClass, true, false);
          isMatched = beanNamesForType.length > 0;
        }
      }
    }
    return isMatched;
  }
}
