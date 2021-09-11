package org.example.alvin.springexamples.bean.scope;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("refreshScope")
public class CustomScopeBean {

  private String username;
}
