package org.example.alvin.springexamples.annotation.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DI {

  /*
  Ĭ�����ε�������ʵ����
   */
  String value() default "all";

  /*
  ֻ����ָ���ļ���ʵ����
   */
  String[] serverIds() default {};

  boolean required() default true;
}
