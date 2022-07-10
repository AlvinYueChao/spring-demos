package dubbo.provider.anno.basic;

import com.alibaba.dubbo.config.annotation.Service;
import dubbo.api.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Override
  public String queryUser(String userId) {
    log.info("====== invoke UserService.queryUser. userId: {} ======", userId);
    return "OK---" + userId;
  }
}
