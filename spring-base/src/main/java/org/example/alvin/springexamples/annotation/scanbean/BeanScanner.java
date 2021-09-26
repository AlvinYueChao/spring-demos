package org.example.alvin.springexamples.annotation.scanbean;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BeanScannerRegistrar.class)
public @interface BeanScanner {


  String[] value() default {};

  String[] basePackages() default {};

  Class<? extends Annotation> annotationClass() default Annotation.class;
}
