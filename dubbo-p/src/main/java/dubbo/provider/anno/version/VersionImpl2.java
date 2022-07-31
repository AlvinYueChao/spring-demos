package dubbo.provider.anno.version;

import dubbo.api.version.Version;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService(version = "1.0.1")
public class VersionImpl2 implements Version {

  @Override
  public String doSomething(String name) {
    log.info("====== 1.0.1 doSomething ======");
    return "OK===" + name;
  }
}
