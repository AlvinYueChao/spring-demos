package org.example.alvin.springexamples.annotation.condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Conditional;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(value = {ClassesCondition.class})
public @interface ConditionOnClasses {

  Class<?>[] value() default {};
  String[] classNames() default {};
}
