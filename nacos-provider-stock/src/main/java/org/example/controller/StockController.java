package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/stock")
public class StockController {

  @PostMapping(value = "/deduct")
  public String deduct(@RequestParam("id") Long stockId, @RequestParam("count") Integer deductCount) {
    try {
      log.info("交易进行中...");
      Thread.sleep(2000);
      log.info("商品: {}, 扣减库存: {} 成功!", stockId, deductCount);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.warn("交易被打断");
    }
    return "success";
  }
}
