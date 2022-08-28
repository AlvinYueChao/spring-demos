package org.example.alvin.springexamples.annotation.scanbean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ImportBeanScanner implements ImportSelector {
  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return new String[]{ImportBeanScannerPostProcessor.class.getName()};
  }
}
