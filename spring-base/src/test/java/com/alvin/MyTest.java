package com.alvin;

import com.alvin.service.UserService;
import com.spring.MyApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class MyTest {

  @Test
  void test1() {
    MyApplicationContext applicationContext = new MyApplicationContext(AppConfig.class);
    log.info("{}", applicationContext.getBean("userService"));
    log.info("{}", applicationContext.getBean("userService"));
    log.info("{}", applicationContext.getBean("orderService"));
  }

  @Test
  void test2() {
    MyApplicationContext applicationContext = new MyApplicationContext(AppConfig.class);
    UserService userService = (UserService) applicationContext.getBean("userService");
    userService.test1();
  }
}
