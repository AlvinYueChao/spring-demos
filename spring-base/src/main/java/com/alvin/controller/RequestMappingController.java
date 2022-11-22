package com.alvin.controller;

import com.alvin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/request")
public class RequestMappingController {

  @Autowired
  private OrderService orderService;

  @GetMapping("/test")
  public Mono<String> testGetMethod() {
    return Mono.just(this.orderService.getOrderInfo());
  }
}
