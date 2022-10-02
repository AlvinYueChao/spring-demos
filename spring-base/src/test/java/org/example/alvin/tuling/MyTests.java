package org.example.alvin.tuling;

import java.beans.Introspector;
import lombok.extern.slf4j.Slf4j;
import org.example.alvin.tuling.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
class MyTests {

  @Test
  void test1() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = applicationContext.getBean(UserService.class);
    userService.test();
    Assertions.assertNotNull(userService);
  }

  @Test
  void test2() {
    log.info("{}", Introspector.decapitalize("BWICProcessor"));
  }
}
