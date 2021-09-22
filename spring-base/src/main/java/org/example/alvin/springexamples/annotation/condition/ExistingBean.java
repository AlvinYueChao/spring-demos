package org.example.alvin.springexamples.annotation.condition;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 0)
public class ExistingBean {

}
