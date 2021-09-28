package org.example.alvin.springexamples.annotation.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DI {

  /*
  默认依次调用所有实现类
   */
  String value() default "all";

  /*
  只调用指定的几个实现类
   */
  String[] serviceIds() default {};

  boolean required() default true;
}
