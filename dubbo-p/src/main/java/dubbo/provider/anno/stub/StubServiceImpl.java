package dubbo.provider.anno.stub;

import dubbo.api.stub.StubService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class StubServiceImpl implements StubService {

  @Override
  public String buyTickets(String name) {
    log.info("{} want to buy a ticket", name);
    return "OK---" + name;
  }
}
