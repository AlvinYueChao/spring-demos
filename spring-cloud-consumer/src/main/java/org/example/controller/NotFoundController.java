package org.example.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class NotFoundController {

  @RequestMapping("/notfound")
  public Mono<Map<String, String>> notFound() {
    HashMap<String, String> map = new HashMap<>();
    map.put("code", "404");
    map.put("data", "Not Found");
    return Mono.just(map);
  }
}
