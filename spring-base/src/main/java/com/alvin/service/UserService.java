package com.alvin.service;

import com.spring.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("userService")
public class UserService {

  public void test() {
    log.info("=== invoked test() ===");
  }
}
