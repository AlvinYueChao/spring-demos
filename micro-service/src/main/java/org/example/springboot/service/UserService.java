package org.example.springboot.service;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserService {

  @Value("${key1}")
  private String value;

  @PostConstruct
  public void init() {
    // priority: command line args > system properties (VM options) > environment variables > application properties (application.yml)
    log.info("value of key1: {}", value);
  }

  public Mono<String> getUserById(long userId) {
    log.info("=== getUserById: {} ===", userId);
    return Mono.just("getUserById: " + userId);
  }
}
