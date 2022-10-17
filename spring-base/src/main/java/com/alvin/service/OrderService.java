package com.alvin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = "prototype")
public class OrderService {

  public void test() {
    log.info("=== invoked test() ===");
  }
}
