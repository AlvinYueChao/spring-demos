package org.example.alvin.springexamples.annotation.condition;

import org.springframework.stereotype.Component;

@Component
@ConditionOnClasses(classNames = {
    "org.example.alvin.springexamples.annotation.condition.ExistingClass"
})
public class ClassesConditionalBean {

}
