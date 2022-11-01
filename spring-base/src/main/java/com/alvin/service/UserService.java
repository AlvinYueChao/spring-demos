package com.alvin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
public class UserService {

  public void test() {
    log.info("=== invoked test() ===");
  }

  public void test1() {
    log.info("{}", a());
  }

  @Lookup("orderService")
  public OrderService a() {
    return null;
  }
}
