package org.example.alvin.springexamples.annotation.scanbean;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
// 1. 自定义注解，然后通过实现 ImportBeanDefinitionRegistrar 接口，读取自定义注解的属性，最终实现将 basePackages 下的所有类注册到 spring 容器中
//@BeanScanner(value = {"org.example.alvin.springexamples.annotation.scanbean.mybasepackage"})
@Import({ImportBeanScanner.class})
public class ScanBean {

}
