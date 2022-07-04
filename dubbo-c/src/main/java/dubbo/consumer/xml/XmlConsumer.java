package dubbo.consumer.xml;

import dubbo.api.service.UserService;
import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlConsumer {

  public static void main(String[] args) throws IOException {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationConsumer.xml");
    UserService userServiceImpl = (UserService) applicationContext.getBean("userServiceImpl");
    userServiceImpl.queryUser("22");
    System.in.read();
  }
}
