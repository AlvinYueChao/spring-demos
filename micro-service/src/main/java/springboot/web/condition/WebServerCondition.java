package springboot.web.condition;

import java.util.Map;
import java.util.Objects;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WebServerCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

    Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnWebServerClass.class.getName());
    String className = (String) Objects.requireNonNull(annotationAttributes).get("value");

    try {
      Objects.requireNonNull(context.getClassLoader()).loadClass(className);
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }
}
