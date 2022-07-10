package dubbo.consumer.anno;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import dubbo.api.group.Group;
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

  /**
   * <pre>
   *   cluster:
   *    - failover: ʧ��ת�ơ�������ʧ�ܣ�����������������at least once, ͨ�������ݵȶ����������Ի���������ӳ٣���ͨ�� retries = 2 ���������Դ�����������һ�Σ�
   *    - failfast: ����ʧ�ܡ�ֻ����һ�ε��ã�ʧ����������ͨ�����ڷ��ݵ�д����
   *    - failsafe: ʧ�ܰ�ȫ�������쳣ʱ��ֱ�Ӻ��ԣ�at most once��ͨ������д�������־�Ȳ���
   *    - failback: ʧ���Զ��ָ�����̨��¼ʧ�����󣬶�ʱ�ط���ͨ��������Ϣ֪ͨ����
   *    - forking: ���е��ö������ֻҪһ���ɹ������ء�ͨ������ʵʱ��Ҫ��ϸߵĶ�����������Ҫ�˷Ѹ���ķ�������Դ
   *    - broadcase: �㲥���á�������ã�����һ̨�����򱨴�ͨ������֪ͨ���з����ṩ֮���»������־�ȱ�����Դ��Ϣ
   *   loadbalance:
   *    - random: ����Ȩ������㷨
   *    - roundrobin: ���ڼ�Ȩ��ѯ�㷨
   *    - leastactive: �������ٻ�Ծ����
   * </pre>
   */
  @Reference(check = false, retries = 3, cluster = "failover", loadbalance = "random")
  private UserService userService;

  /**
   * group = "*": ���������һ provider
   * group = "groupImpl1": ָ������ groupImpl1 �µ� provider
   */
  @Reference(check = false, group = "*", parameters = {"merger", "true"})
  private Group group;

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
    log.info(group.doSomething("111"));
  }
}
