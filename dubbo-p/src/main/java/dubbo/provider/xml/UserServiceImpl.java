package dubbo.provider.xml;

import dubbo.api.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {

  @Override
  public String queryUser(String userId) {
    log.info("====== invoke UserService.queryUser ======");
    return "OK";
  }
}
