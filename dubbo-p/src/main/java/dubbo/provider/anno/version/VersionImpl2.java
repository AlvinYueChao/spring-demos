package dubbo.provider.anno.version;

import com.alibaba.dubbo.config.annotation.Service;
import dubbo.api.version.Version;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(version = "1.0.1")
public class VersionImpl2 implements Version {

  @Override
  public String doSomething(String name) {
    log.info("====== 1.0.1 doSomething ======");
    return "OK===" + name;
  }
}
