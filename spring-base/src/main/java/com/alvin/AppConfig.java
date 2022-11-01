package com.alvin;

import com.alvin.service.OrderService;
import com.alvin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan("com.alvin.service")
@Configuration
@Slf4j
public class AppConfig {

  @Bean
  public OrderService orderService() {
    log.info("{}", userService());
    log.info("{}", userService());
    return new OrderService();
  }

  @Bean
  public UserService userService() {
    return new UserService();
  }
}
