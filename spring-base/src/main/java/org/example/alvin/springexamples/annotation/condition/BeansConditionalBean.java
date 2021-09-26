package org.example.alvin.springexamples.annotation.condition;

import org.springframework.stereotype.Component;

@Component
@ConditionOnBeans(value = {ExistingBean.class})
public class BeansConditionalBean {
}
