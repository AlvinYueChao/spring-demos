package com.alvin.service;

import com.spring.Autowired;
import com.spring.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("userService")
public class UserService {

  @Autowired
  private OrderService orderService;

  public void test() {
    log.info("=== invoked test() ===");
  }

  public void test1() {
    log.info("{}", this.orderService);
  }
}
