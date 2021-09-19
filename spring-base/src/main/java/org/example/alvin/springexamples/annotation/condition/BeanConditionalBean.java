package org.example.alvin.springexamples.annotation.condition;

import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(value = {ExistingBean.class})
public class BeanConditionalBean {

}
