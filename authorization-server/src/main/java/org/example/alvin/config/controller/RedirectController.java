package org.example.alvin.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RedirectController {

  @GetMapping("/sayHello")
  public void showCode(@RequestParam("name") String name) {
    log.info("hello {}, welcome to the authorization server", name);
  }
}
