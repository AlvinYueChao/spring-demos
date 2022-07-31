package dubbo.provider.anno.group;

import dubbo.api.group.Group;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService(group = "groupImpl2")
public class GroupImpl2 implements Group {

  @Override
  public String doSomething(String name) {
    log.info("====== invoke GroupImpl2.doSomething ======");
    return name + " do something under groupImpl2";
  }

  @Override
  public double[] doSomething(double[] values) {
    return values;
  }
}
