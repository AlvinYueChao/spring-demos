package org.example.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@FeignClient(name = "service-provider")
public interface ServiceProviderClient {

  @RequestMapping("/hello")
  public Mono<String> hello();
}
