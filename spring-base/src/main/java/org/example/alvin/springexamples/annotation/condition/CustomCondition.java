package org.example.alvin.springexamples.annotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CustomCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//    // �� matches() ����Ϊ false ʱ��@Component ���ڵ� bean ���Ͳ�δ��ע�ᵽ spring �����У����� getBean() �ᱨ��
//    return false;
    return true;
  }
}
