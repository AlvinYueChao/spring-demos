package org.example.alvin.springexamples.annotation.spi;

import java.util.List;
import org.example.alvin.springexamples.annotation.spi.handler.InvokeHandler;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public class AutoConfigurationImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    List<String> invokeHandlerClassNames = SpringFactoriesLoader.loadFactoryNames(InvokeHandler.class, ClassUtils.getDefaultClassLoader());
    invokeHandlerClassNames.add(DIAnnotationBeanPostProcessor.class.getName());
    return StringUtils.toStringArray(invokeHandlerClassNames);
  }
}
