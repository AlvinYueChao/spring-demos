package org.example.alvin.springexamples.annotation.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(value = {CustomCondition.class, CustomCondition1.class})
public class ConditionalBean {

}
