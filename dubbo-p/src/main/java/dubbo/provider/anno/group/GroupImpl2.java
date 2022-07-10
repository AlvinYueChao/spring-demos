package dubbo.provider.anno.group;

import com.alibaba.dubbo.config.annotation.Service;
import dubbo.api.group.Group;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(group = "groupImpl2")
public class GroupImpl2 implements Group {

  @Override
  public String doSomething(String name) {
    log.info("====== invoke GroupImpl2.doSomething ======");
    return name + " do something under groupImpl2";
  }
}
