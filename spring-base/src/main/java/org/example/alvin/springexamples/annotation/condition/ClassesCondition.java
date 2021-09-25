package org.example.alvin.springexamples.annotation.condition;

import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

public class ClassesCondition implements Condition {

  private final Logger logger = LogManager.getLogger(ClassesCondition.class);

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    boolean isMatched = false;
    if (metadata.isAnnotated(ConditionOnClasses.class.getName())) {
      Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionOnClasses.class.getName());
      try {
        Object value = Objects.requireNonNull(annotationAttributes).get("classNames");
        if (value instanceof String[]) {
          for (String className : (String[]) value) {
            ClassUtils.forName(className, ClassUtils.getDefaultClassLoader());
          }
          isMatched = true;
        }
      } catch (Exception e) {
        logger.warn("Caught exception when checking matched conditions", e);
      }
    }
    return isMatched;
  }
}
