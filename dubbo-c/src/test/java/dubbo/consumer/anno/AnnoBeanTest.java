package dubbo.consumer.anno;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
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
  }

  @Test
  void test2() {
    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName("dubbo_consumer");

    ConsumerConfig consumerConfig = new ConsumerConfig();
    consumerConfig.setCheck(false);

    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setAddress("zookeeper://192.168.20.10:2181");

    ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
    referenceConfig.setApplication(applicationConfig);
    referenceConfig.setConsumer(consumerConfig);
    referenceConfig.setRegistry(registryConfig);
    referenceConfig.setInterface(UserService.class);
    UserService userService1 = referenceConfig.get();
    log.info("manually invoke result: {}", userService1.queryUser("44"));
  }
}
