package org.example.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserService {

  public Mono<String> getUserById(long userId) {
    log.info("=== getUserById: {} ===", userId);
    return Mono.just("getUserById: " + userId);
  }
}
