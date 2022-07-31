package dubbo.provider.anno.group;

import dubbo.api.group.Group;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService(group = "groupImpl1")
public class GroupImpl1 implements Group {

  @Override
  public String doSomething(String name) {
    log.info("====== invoke GroupImpl1.doSomething ======");
    return name + " do something under groupImpl1";
  }

  @Override
  public double[] doSomething(double[] values) {
    log.info("====== invoke GroupImpl1.doSomething ======");
    return values;
  }
}
