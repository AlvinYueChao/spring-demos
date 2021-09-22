package org.example.alvin.springexamples.annotation.condition;

import org.springframework.core.annotation.Order;

//@Component
@ConditionalOnBean(value = {ExistingBean.class})
@Order
public class BeanConditionalBean {
}
