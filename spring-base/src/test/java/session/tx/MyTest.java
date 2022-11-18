package session.tx;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
class MyTest {

  @Test
  void test1() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    log.info("{}", applicationContext);
  }
}
