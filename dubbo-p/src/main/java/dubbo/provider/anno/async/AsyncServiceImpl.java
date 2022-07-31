package dubbo.provider.anno.async;

import dubbo.api.async.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService(timeout = 20000)
public class AsyncServiceImpl implements AsyncService {

  @Override
  public String asyncToDo(String name) {
    try {
      log.info("Long term business logic executing...");
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.warn("Interrupted unexpectedly, ", e);
    }
    return "OK---" + name;
  }
}
