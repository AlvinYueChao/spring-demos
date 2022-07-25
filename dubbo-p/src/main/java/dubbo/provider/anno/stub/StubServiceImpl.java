package dubbo.provider.anno.stub;

import com.alibaba.dubbo.config.annotation.Service;
import dubbo.api.stub.StubService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StubServiceImpl implements StubService {

  @Override
  public String buyTickets(String name) {
    log.info("{} want to buy a ticket", name);
    return "OK---" + name;
  }
}
