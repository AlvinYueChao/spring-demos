package org.example.controller;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class HelloController {

  @Value("${spring.cloud.provider.name}")
  private String providerName;

  @Value("${server.port}")
  private String port;

  @RequestMapping("/hello")
  public Mono<String> hello() {
    OffsetDateTime now = OffsetDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
    String responseStr = String.format("provider name: %s, port: %s, datetime: %s", providerName, port, dateTimeFormatter.format(now));
    log.info(responseStr);
    return Mono.just(responseStr);
  }
}
