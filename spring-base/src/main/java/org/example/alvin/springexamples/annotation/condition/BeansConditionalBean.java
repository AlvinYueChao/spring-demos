package org.example.alvin.springexamples.annotation.condition;

import org.springframework.core.annotation.Order;

//@Component
@ConditionOnBeans(value = {ExistingBean.class})
@Order
public class BeansConditionalBean {
}
