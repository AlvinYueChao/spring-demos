package org.example.alvin.springexamples.annotation.condition;

import org.springframework.stereotype.Component;

@Component
@ConditionOnProperties(propertyNames = {
    "conditionalProperty.filledValue", "conditionalProperty.emptyValue"
})
public class PropertiesConditionalBean {

}
