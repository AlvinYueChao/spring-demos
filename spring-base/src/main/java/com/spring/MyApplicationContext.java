package com.spring;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

@Slf4j
public class MyApplicationContext {

  private final Class<?> configClass;
  private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

  public MyApplicationContext(Class<?> configClass) {
    this.configClass = configClass;

    // 扫描
    if (configClass.isAnnotationPresent(ComponentScan.class)) {
      ComponentScan componentScanAnnotation = configClass.getAnnotation(ComponentScan.class);
      String path = componentScanAnnotation.value();
      path = ClassUtils.convertClassNameToResourcePath(path);
      var fullPathPattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + path + "**/*.class";
      try {
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(fullPathPattern);
        for (Resource resource : resources) {
          String fullPath = resource.getFile().getPath();
          String classPathFromRoot = fullPath.substring(fullPath.indexOf(path.split("/")[0]), fullPath.indexOf(".class"));
          classPathFromRoot = classPathFromRoot.replace("\\", ".");
          Class<?> aClass = Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).loadClass(classPathFromRoot);
          if (aClass.isAnnotationPresent(Component.class)) {

            Component componentAnnotation = aClass.getAnnotation(Component.class);
            String beanName = componentAnnotation.value();
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setType(aClass);

            if (aClass.isAnnotationPresent(Scope.class)) {
              Scope scopeAnnotation = aClass.getAnnotation(Scope.class);
              String scopeValue = scopeAnnotation.value();
              beanDefinition.setScope(scopeValue);
            } else {
              beanDefinition.setScope("singleton");
            }
            beanDefinitionMap.put(beanName, beanDefinition);
          }
        }
      } catch (IOException | ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
      log.info(beanDefinitionMap.toString());
    }
  }

  public Object getBean(String beanName) {
    //todo: implement
    return null;
  }
}
