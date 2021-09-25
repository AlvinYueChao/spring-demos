package org.example.alvin.springexamples.annotation.condition;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

public class PropertiesCondition implements Condition {

  private final Logger logger = LogManager.getLogger(PropertiesCondition.class);

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    boolean isMatched = false;
    if (metadata.isAnnotated(ConditionOnProperties.class.getName())) {
      Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionOnProperties.class.getName());
      Object propertyNames = Objects.requireNonNull(annotationAttributes).get("propertyNames");
      if (propertyNames instanceof String[]) {
        try {
          Properties allProperties = PropertiesLoaderUtils.loadAllProperties("application.properties", ClassUtils.getDefaultClassLoader());
          boolean isBrokeBecauseNotMatched = false;
          for (String propertyName : ((String[]) propertyNames)) {
            String property = allProperties.getProperty(propertyName);
            if (property == null) {
              isBrokeBecauseNotMatched = true;
              break;
            }
          }
          isMatched = !isBrokeBecauseNotMatched;
        } catch (IOException e) {
          logger.error("Cannot access properties file: application.properties, please check it", e);
        }
      }
    }
    return isMatched;
  }
}
