package springboot.autoconfigure;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class AlvinImportSelector implements DeferredImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    // org.springframework.core.io.support.SpringFactoriesLoader.loadFactoryNames 只能load配置在META-INF/spring.factories中的Type类型的实现类
    ServiceLoader<AutoConfiguration> serviceLoader = ServiceLoader.load(AutoConfiguration.class);
    List<String> autoConfigurationClassNames = new ArrayList<>();
    for (AutoConfiguration autoConfiguration : serviceLoader) {
      autoConfigurationClassNames.add(autoConfiguration.getClass().getName());
    }
    return autoConfigurationClassNames.toArray(new String[0]);
  }
}
