package com.alvin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderService {

  public void test() {
    log.info("=== invoked test() ===");
  }

  public String getOrderInfo() {
    return "Simple Order";
  }
}
