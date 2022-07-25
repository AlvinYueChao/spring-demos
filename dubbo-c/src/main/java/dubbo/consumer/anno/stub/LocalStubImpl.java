package dubbo.consumer.anno.stub;

import dubbo.api.stub.StubService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalStubImpl implements StubService {

  private final StubService stubService;

  public LocalStubImpl(StubService stubService) {
    this.stubService = stubService;
  }

  @Override
  public String buyTickets(String name) {
    try {
      return this.stubService.buyTickets(name);
    } catch (Exception e) {
      log.warn("Caught exception, service downgrade...", e);
      return "Ticket service is down, please buy your ticket later";
    }
  }
}
