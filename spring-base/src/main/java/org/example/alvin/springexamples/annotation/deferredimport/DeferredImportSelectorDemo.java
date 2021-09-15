package org.example.alvin.springexamples.annotation.deferredimport;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;

public class DeferredImportSelectorDemo implements DeferredImportSelector {

  private final Logger logger = LogManager.getLogger(DeferredImportSelectorDemo.class);

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    MergedAnnotations annotations = importingClassMetadata.getAnnotations();
    MergedAnnotation<EnableAspectJAutoProxy> aspectAnnotation = annotations.get(EnableAspectJAutoProxy.class);
    Optional<Object> proxyTargetClass = aspectAnnotation.getValue("proxyTargetClass");
    if (proxyTargetClass.isPresent()) {
      Object o = proxyTargetClass.get();
      logger.info("{}", o);
    }
    return new String[0];
  }
}
