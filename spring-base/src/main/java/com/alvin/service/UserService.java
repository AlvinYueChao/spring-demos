package com.alvin.service;

import com.spring.Autowired;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserService {

//  @Autowired
  @Resource
  private OrderService orderService;

  public void test() {
    log.info("=== invoked test() ===");
  }

  public void test1() {
    log.info("{}", this.orderService);
  }
}
