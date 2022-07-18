package dubbo.provider.anno.version;

import com.alibaba.dubbo.config.annotation.Service;
import dubbo.api.version.Version;

@Service(version = "1.0.0")
public class VersionImpl1 implements Version {

  @Override
  public String doSomething(String name) {
    return name + " call doSomething v1.0.0";
  }
}
