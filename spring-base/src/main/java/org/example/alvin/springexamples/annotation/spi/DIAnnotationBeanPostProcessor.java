package org.example.alvin.springexamples.annotation.spi;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.annotation.InjectionMetadata.InjectedElement;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/*
模仿 AutowiredAnnotationBeanPostProcessor 对 @Autowired 和 @Value 注解的支持
注解收集：模仿 org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyMergedBeanDefinitionPostProcessors，需要实现 MergedBeanDefinitionPostProcessor 接口
依赖注入：模仿 org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean, 需要实现 InstantiationAwareBeanPostProcessor 接口
 */
public class DIAnnotationBeanPostProcessor implements MergedBeanDefinitionPostProcessor, InstantiationAwareBeanPostProcessor {

  private final Logger logger = LogManager.getLogger(DIAnnotationBeanPostProcessor.class);

  private final Map<String, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<>(256);
  private final Set<Class<? extends Annotation>> diAnnotationTypes = new LinkedHashSet<>(4);
  private String requiredParameterName = "required";
  private boolean requiredParameterValue = true;

  public DIAnnotationBeanPostProcessor() {
    this.diAnnotationTypes.add(DI.class);
  }

  @Override
  public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
    InjectionMetadata metadata = findDIMetadata(beanName, beanType, null);
  }

  private InjectionMetadata findDIMetadata(String beanName, Class<?> clazz, @Nullable PropertyValues pvs) {
    // Fall back to class name as cache key, for backwards compatibility with custom callers.
    String cacheKey = (StringUtils.hasLength(beanName) ? beanName : clazz.getName());
    // Quick check on the concurrent map first, with minimal locking.
    InjectionMetadata metadata = this.injectionMetadataCache.get(cacheKey);
    if (InjectionMetadata.needsRefresh(metadata, clazz)) {
      synchronized (this.injectionMetadataCache) {
        metadata = this.injectionMetadataCache.get(cacheKey);
        if (InjectionMetadata.needsRefresh(metadata, clazz)) {
          if (metadata != null) {
            metadata.clear(pvs);
          }
          // 重点看
          metadata = buildDIMetadata(clazz);
          this.injectionMetadataCache.put(cacheKey, metadata);
        }
      }
    }
    return metadata;
  }

  private InjectionMetadata buildDIMetadata(final Class<?> clazz) {
    if (!AnnotationUtils.isCandidateClass(clazz, this.diAnnotationTypes)) {
      return InjectionMetadata.EMPTY;
    }

    List<InjectedElement> elements = new ArrayList<>();
    Class<?> targetClass = clazz;

    do {
      final List<InjectionMetadata.InjectedElement> currElements = new ArrayList<>();

      ReflectionUtils.doWithLocalFields(targetClass, field -> {
        MergedAnnotation<?> ann = findDIAnnotation(field);
        if (ann != null) {
          if (Modifier.isStatic(field.getModifiers())) {
            if (logger.isInfoEnabled()) {
              logger.info("Autowired annotation is not supported on static fields: " + field);
            }
            return;
          }
          boolean required = determineRequiredStatus(ann);
          currElements.add(new DIFieldElement(field, required));
        }
      });

      ReflectionUtils.doWithLocalMethods(targetClass, method -> {
        Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
        if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod)) {
          return;
        }
        MergedAnnotation<?> ann = findDIAnnotation(bridgedMethod);
        if (ann != null && method.equals(ClassUtils.getMostSpecificMethod(method, clazz))) {
          if (Modifier.isStatic(method.getModifiers())) {
            if (logger.isInfoEnabled()) {
              logger.info("Autowired annotation is not supported on static methods: " + method);
            }
            return;
          }
          if (method.getParameterCount() == 0) {
            if (logger.isInfoEnabled()) {
              logger.info("Autowired annotation should only be used on methods with parameters: " +
                  method);
            }
          }
          boolean required = determineRequiredStatus(ann);
          PropertyDescriptor pd = BeanUtils.findPropertyForMethod(bridgedMethod, clazz);
          currElements.add(new DIMethodElement(method, required, pd));
        }
      });

      elements.addAll(0, currElements);
      targetClass = targetClass.getSuperclass();
    }
    while (targetClass != null && targetClass != Object.class);

    return InjectionMetadata.forElements(elements, clazz);
  }

  private boolean determineRequiredStatus(MergedAnnotation<?> ann) {
    // The following (AnnotationAttributes) cast is required on JDK 9+.
    return determineRequiredStatus((AnnotationAttributes)
        ann.asMap(mergedAnnotation -> new AnnotationAttributes(mergedAnnotation.getType())));
  }

  private boolean determineRequiredStatus(AnnotationAttributes ann) {
    return (!ann.containsKey(this.requiredParameterName) ||
        this.requiredParameterValue == ann.getBoolean(this.requiredParameterName));
  }

  private MergedAnnotation<?> findDIAnnotation(AccessibleObject ao) {
    MergedAnnotations annotations = MergedAnnotations.from(ao);
    for (Class<? extends Annotation> type : this.diAnnotationTypes) {
      MergedAnnotation<?> annotation = annotations.get(type);
      if (annotation.isPresent()) {
        return annotation;
      }
    }
    return null;
  }

  @Override
  public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
    InjectionMetadata metadata = findDIMetadata(beanName, bean.getClass(), pvs);
    try {
      metadata.inject(bean, beanName, pvs);
    }
    catch (BeanCreationException ex) {
      throw ex;
    }
    catch (Throwable ex) {
      throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);
    }
    return pvs;
  }

  private static class DIFieldElement extends InjectedElement {

    private final boolean required;

    public DIFieldElement(Field field, boolean required) {
      super(field, null);
      this.required = required;
    }

    @Override
    protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
      // todo
    }
  }

  private static class DIMethodElement extends InjectedElement {

    private final boolean required;

    public DIMethodElement(Method method, boolean required, @Nullable PropertyDescriptor pd) {
      super(method, pd);
      this.required = required;
    }

    @Override
    protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
      // todo
    }
  }
}
