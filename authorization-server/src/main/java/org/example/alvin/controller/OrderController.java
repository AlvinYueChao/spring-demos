package org.example.alvin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

  @GetMapping("/getOrderById")
  public String getOrderById(@RequestParam("id") Long id) {
    return "ok";
  }
}
