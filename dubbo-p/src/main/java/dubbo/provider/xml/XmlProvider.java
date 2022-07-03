package dubbo.provider.xml;

import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlProvider {

  public static void main(String[] args) throws IOException {
    new ClassPathXmlApplicationContext("applicationProvider.xml");
    System.in.read();
  }
}
