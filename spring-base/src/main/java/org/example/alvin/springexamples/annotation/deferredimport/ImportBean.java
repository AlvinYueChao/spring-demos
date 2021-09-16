package org.example.alvin.springexamples.annotation.deferredimport;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(value = {DeferredImportSelectorDemo.class, ImportBeanDefinitionRegistrarDemo.class})
//@EnableAspectJAutoProxy
public class ImportBean {

}
