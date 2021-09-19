package org.example.alvin.springexamples.annotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CustomCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//    // 当 matches() 返回为 false 时，@Component 所在的 bean 类型并未被注册到 spring 容器中，所以 getBean() 会报错
//    return false;
    return true;
  }
}
