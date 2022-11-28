package org.example.springboot.controller;

import org.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

  @Autowired
  private UserService userService;

  @GetMapping("/getUserById")
  public Mono<String> getUserById(@RequestParam Long userId) {
    return userService.getUserById(userId);
  }
}
