package dubbo.consumer.anno;

import dubbo.api.async.AsyncService;
import dubbo.api.callback.CallbackService;
import dubbo.api.group.Group;
import dubbo.api.mock.MockService;
import dubbo.api.service.UserService;
import dubbo.api.stub.StubService;
import dubbo.api.validation.Validation;
import dubbo.api.validation.ValidationParameter;
import dubbo.api.version.Version;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
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
  @DubboReference(check = false, retries = 3, cluster = "failover", loadbalance = "random")
  private UserService userService;

  /**
   * group = "*": ���������һ provider group = "groupImpl1": ָ������ groupImpl1 �µ� provider
   */
  @DubboReference(check = false, group = "*", parameters = {"merger", "true"})
  private Group group;

  /**
   * version = "*": ���������һ�汾�� provider
   */
  @DubboReference(check = false, version = "1.0.0")
  private Version version;

  @DubboReference(check = false, validation = "true")
  private Validation validation;

  @DubboReference(check = false, methods = {@Method(name = "asyncToDo", async = true)})
  private AsyncService asyncService;

  @DubboReference(check = false)
  private CallbackService callbackService;

  @DubboReference(check = false, stub = "dubbo.consumer.anno.stub.LocalStubImpl")
  private StubService stubService;

  @DubboReference(check = false, mock = "true")
  private MockService mockService;

  @Test
  void testBasic() {
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
    // hibernate-validator ����У����Ϣ�й��ʻ������ļ�������ʽָ��
    Locale.setDefault(new Locale("en", "US"));
    // validation success
    ValidationParameter validationParameter = ValidationParameter.builder().age(18).name("Alvin").loginDate(LocalDate.now().minusDays(1)).expiryDate(LocalDate.now().plusDays(1)).build();
    log.info("validation success: {}", validation.checkBeforeSomething(validationParameter));

    // validation failed
    ValidationParameter validationParameter1 = ValidationParameter.builder().age(101).name("Alvin").loginDate(LocalDate.now()).expiryDate(LocalDate.now().plusDays(1)).build();
    log.info("validation failed: {}", validation.checkBeforeSomething(validationParameter1));
  }

  @Test
  void testGeneric() {
    // ��Ҫ�������ԣ�ʵ���������������õ�
    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName("dubbo_consumer");

    ConsumerConfig consumerConfig = new ConsumerConfig();
    consumerConfig.setCheck(false);

    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setAddress("zookeeper://192.168.20.10:2181");

    ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
    referenceConfig.setApplication(applicationConfig);
    referenceConfig.setConsumer(consumerConfig);
    referenceConfig.setRegistry(registryConfig);
    referenceConfig.setInterface("dubbo.api.service.UserService");
    // ��������
    referenceConfig.setGeneric("true");
    GenericService userService = referenceConfig.get();
    Object result = userService.$invoke("queryUser", new String[]{"java.lang.String"}, new Object[]{"Alvin"});
    log.info("use manual genericService to call queryUser, result: {}", result);
  }

  @Test
  void testAsync() throws ExecutionException, InterruptedException {
    String result = asyncService.asyncToDo("Alvin");
    log.info("async result: {}", result);
    // �������߳�, ���ܽϵ�
    Object blockedResult = RpcContext.getContext().getFuture().get();
    log.info("blocked async result: {}", blockedResult);
  }

  @Test
  void testParameterCallback() {
    String result = callbackService.addListener("Alvin", name -> "====== client call doListen ====== " + name);
    log.info("Invoke callbackService result: {}", result);
  }

  @Test
  void testStub() {
    log.info("Want to buy a ticket, result: {}", stubService.buyTickets("Alvin"));
  }

  @Test
  void testMock1() {
    log.info("MockService.mock result: {}", mockService.mock("Alvin"));
  }
}
