package com.alvin;

import com.alvin.service.UserService;
import com.spring.MyApplicationContext;
import org.junit.jupiter.api.Test;

class MyTest {

  @Test
  void test1() {
    MyApplicationContext applicationContext = new MyApplicationContext(AppConfig.class);
    UserService userService = (UserService) applicationContext.getBean("userService");
    userService.test();
  }
}
