package org.example.alvin.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
  @GetMapping(value = {"/api/special1", "/api/special2"})
  public String specialEndpoint() {
    return "Hello World";
  }

  @GetMapping(value = {"/rest/hello"})
  public String sayHello(@RequestParam String name) {
    return "Hello " + name;
  }
}
