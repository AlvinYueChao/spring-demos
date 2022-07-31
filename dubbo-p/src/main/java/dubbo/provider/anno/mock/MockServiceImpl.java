package dubbo.provider.anno.mock;

import dubbo.api.mock.MockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class MockServiceImpl implements MockService {

  @Override
  public String mock(String name) {
    log.info("====== {}.mock ======", this.getClass().getName());
    return "OK---" + name;
  }
}
