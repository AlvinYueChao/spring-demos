package dubbo.consumer.anno;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import dubbo.api.group.Group;
import dubbo.api.service.UserService;
import dubbo.api.validation.Validation;
import dubbo.api.validation.ValidationParameter;
import dubbo.api.version.Version;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AnnoBean.class})
class AnnoBeanTest {

  /**
   * <pre>
   *   cluster:
   *    - failover: 失败转移。当出现失败，重试其他服务器。at least once, 通常用于幂等读操作，重试会带来更长延迟，可通过 retries = 2 来设置重试次数（不含第一次）
   *    - failfast: 快速失败。只发起一次调用，失败立即报错。通常用于非幂等写操作
   *    - failsafe: 失败安全。出现异常时，直接忽略，at most once，通常用于写入审计日志等操作
   *    - failback: 失败自动恢复。后台记录失败请求，定时重发，通常用于消息通知操作
   *    - forking: 并行调用多个服务，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多的服务器资源
   *    - broadcase: 广播调用。逐个调用，任意一台报错则报错。通常用于通知所有服务提供之更新缓存或日志等本地资源信息
   *   loadbalance:
   *    - random: 基于权重随机算法
   *    - roundrobin: 基于加权轮询算法
   *    - leastactive: 基于最少活跃调用
   * </pre>
   */
  @Reference(check = false, retries = 3, cluster = "failover", loadbalance = "random")
  private UserService userService;

  /**
   * group = "*": 随机访问任一 provider group = "groupImpl1": 指定访问 groupImpl1 下的 provider
   */
  @Reference(check = false, group = "*", parameters = {"merger", "true"})
  private Group group;

  /**
   * version = "*": 随机访问任一版本的 provider
   */
  @Reference(check = false, version = "1.0.0")
  private Version version;

  @Reference(check = false, validation = "true")
  private Validation validation;

  @Test
  void testBasic() throws IOException {
    log.info("invoke result: {}", userService.queryUser("33"));
  }

  @Test
  void testManualSetupReferenceBean() {
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

  /**
   * /@Reference(check = false, group = "*")
   */
  @Test
  void testGroup() {
    log.info(group.doSomething("Alvin"));
  }

  /**
   * /@Reference(check = false, group = "*", parameters = {"merger", "true"})
   */
  @Test
  void testGroupMerge() {
    // custom merger for String
    log.info(group.doSomething("111"));

    // default merger for double array
    log.info("merge two double array into ones: {}", Arrays.toString(group.doSomething(new double[]{10.1d})));
  }

  @Test
  void testVersion() {
    log.info(version.doSomething("Alvin"));
  }

  @Test
  void testValidation() {
    // validation success
    ValidationParameter validationParameter = ValidationParameter.builder().age(18).name("Alvin").loginDate(LocalDate.now().minusDays(1)).expiryDate(LocalDate.now().plusDays(1)).build();
    log.info("validation success: {}", validation.checkBeforeSomething(validationParameter));

    // validation failed
    ValidationParameter validationParameter1 = ValidationParameter.builder().age(101).name("Alvin").loginDate(LocalDate.now()).expiryDate(LocalDate.now().plusDays(1)).build();
    log.info("validation failed: {}", validation.checkBeforeSomething(validationParameter1));
  }
}
