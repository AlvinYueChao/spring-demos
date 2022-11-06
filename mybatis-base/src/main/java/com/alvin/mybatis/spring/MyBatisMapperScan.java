package com.alvin.mybatis.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({MyBatisImportBeanDefinitionRegister.class})
/**
 * solution3: 通过自定义注解扫描，将指定package下的所有接口通过MyBatisFactoryBean生成对应的BeanDefinition加入spring容器中
 */
public @interface MyBatisMapperScan {

  String value();
}
