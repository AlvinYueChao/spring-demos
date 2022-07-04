package dubbo.provider.anno;

import java.io.IOException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnoProvider {

  public static void main(String[] args) throws IOException {
    new AnnotationConfigApplicationContext(AnnoBean.class);
    System.in.read();
  }
}
