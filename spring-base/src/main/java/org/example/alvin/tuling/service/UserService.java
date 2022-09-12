package org.example.alvin.tuling.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserService {

  private OrderService orderService;

  /*public UserService() {
    log.info("1");
  }*/

  /*@Autowired
  public UserService(OrderService orderService) {
    this.orderService = orderService;
    log.info("2");
  }*/

  public void test() {
    log.info("=== invoked into test() ====");
  }
}
