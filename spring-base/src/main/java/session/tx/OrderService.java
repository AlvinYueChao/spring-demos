package session.tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private OrderService orderService;

  @Transactional
  public void test1() {
    jdbcTemplate.execute("insert into t1 values (1, 1, '1', 1)");

    try {
      /*
      想要触发事务传播，必须把orderService注入进来，只有这样调用a()的才是代理对象，this.a()，this是代理对象中的h，不是代理对象本身
       */
      orderService.a();
    } catch (Exception e) {
      log.warn("catch exception.", e);
    }
  }

  @Transactional
  public void a() {
    jdbcTemplate.execute("insert into t1 values (2, 2, '2', 2)");
    throw new RuntimeException();
  }
}
