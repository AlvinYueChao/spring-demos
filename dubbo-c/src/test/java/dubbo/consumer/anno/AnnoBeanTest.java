package dubbo.consumer.anno;

import com.alibaba.dubbo.config.annotation.Reference;
import dubbo.api.service.UserService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AnnoBean.class})
class AnnoBeanTest {

  @Reference(check = false)
  private UserService userService;

  @Test
  void test1() throws IOException {
    log.info("invoke result: {}", userService.queryUser("33"));
    System.in.read();
  }
}
