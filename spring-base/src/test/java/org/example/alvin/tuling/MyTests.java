package org.example.alvin.tuling;

import org.example.alvin.tuling.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class MyTests {

  private final static String BASE_PACKAGE = "org.example.alvin.tuling";

  @Test
  void test1() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    UserService userService = applicationContext.getBean(UserService.class);
    Assertions.assertNotNull(userService);
  }
}
