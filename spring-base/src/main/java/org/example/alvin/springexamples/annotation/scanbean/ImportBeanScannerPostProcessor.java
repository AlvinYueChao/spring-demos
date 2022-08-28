package org.example.alvin.springexamples.annotation.scanbean;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.NotImplementedException;
import org.example.alvin.springexamples.annotation.spi.DI;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ImportBeanScannerPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        BeanPackageScanner scanner = new BeanPackageScanner(registry);
        Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
        String basePackagesStr = properties.getProperty("auto.load.nonannotated.beans.basepackages");

        List<String> basePackages = new ArrayList<>(Arrays.asList(basePackagesStr.split(",")));

        // 将扫描到的所有的类全都加载到 Spring 容器中
        scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            var annotation = metadataReader.getAnnotationMetadata().getAnnotations().get(CustomComponent.class);
            return annotation.isPresent();
        });
        scanner.doScan(basePackages.toArray(new String[0]));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
