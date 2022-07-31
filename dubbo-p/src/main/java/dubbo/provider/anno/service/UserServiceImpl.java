package dubbo.provider.anno.service;

import dubbo.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class UserServiceImpl implements UserService {

  @Override
  public String queryUser(String userId) {
    log.info("====== invoke UserService.queryUser. userId: {} ======", userId);
    return "OK---" + userId;
  }
}
