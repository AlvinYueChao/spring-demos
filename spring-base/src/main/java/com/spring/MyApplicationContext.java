package com.spring;

import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

@Slf4j
public class MyApplicationContext {

  private final Class<?> configClass;
  private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
  // 单例池
  private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

  private static final String SINGLETON = "singleton";

  public MyApplicationContext(Class<?> configClass) {
    this.configClass = configClass;

    // 扫描
    scan(configClass);

    // 创建单例 Bean
    for (Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
      String beanName = entry.getKey();
      BeanDefinition beanDefinition = entry.getValue();
      if (beanDefinition.getScope().equalsIgnoreCase(SINGLETON)) {
        Object bean = createBean(beanName, beanDefinition);
        singletonObjects.put(beanName, bean);
      }
    }
  }

  private Object createBean(String beanName, BeanDefinition beanDefinition) {
    Class<?> aClass = beanDefinition.getType();
    Object newInstance = null;
    try {
      newInstance = aClass.getConstructor().newInstance();

      // 依赖注入之属性注入
      for (Field field : aClass.getDeclaredFields()) {
        if (field.isAnnotationPresent(Autowired.class)) {
          String injectedBeanName = field.getName();
          // todo: 先根据类型找，再根据名称找
          Object injectedBeanObj = this.getBean(injectedBeanName);
          field.setAccessible(true);
          field.set(newInstance, injectedBeanObj);
        }
      }

      // InitializingBean 初始化 Bean
      if (newInstance instanceof InitializingBean) {
        ((InitializingBean) newInstance).afterPropertiesSet();
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return newInstance;
  }

  public Object getBean(String beanName) {
    if (!beanDefinitionMap.containsKey(beanName)) {
      throw new NullPointerException();
    }

    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
    if (beanDefinition.getScope().equalsIgnoreCase(SINGLETON)) {
      // 单例直接从单例池返回
      return singletonObjects.computeIfAbsent(beanName, s -> {
        Object bean = this.createBean(s, beanDefinition);
        this.singletonObjects.put(s, bean);
        return bean;
      });
    } else {
      // 原型 Bean 每次都创建
      return createBean(beanName, beanDefinition);
    }
  }

  private void scan(Class<?> configClass) {
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
            // 默认 BeanName
            if (StringUtils.isBlank(beanName)) {
              beanName = Introspector.decapitalize(aClass.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setType(aClass);
            if (aClass.isAnnotationPresent(Scope.class)) {
              Scope scopeAnnotation = aClass.getAnnotation(Scope.class);
              String scopeValue = scopeAnnotation.value();
              beanDefinition.setScope(scopeValue);
            } else {
              beanDefinition.setScope(SINGLETON);
            }
            beanDefinitionMap.put(beanName, beanDefinition);
          }
        }
      } catch (IOException | ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
