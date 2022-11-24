package springboot;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import springboot.web.server.WebServer;

@Slf4j
public class AlvinSpringApplication {

  private AlvinSpringApplication() {
  }

  public static void run(Class<?> clazz) {
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.register(clazz);
    applicationContext.refresh();

    WebServer webServer = getWebServer(applicationContext);
    webServer.start();
  }

  private static WebServer getWebServer(WebApplicationContext applicationContext) {
    // 判断依赖的是Tomcat还是Netty,在不加@Primary情况下，只能有一个WebServer类型的bean
    Map<String, WebServer> webServers = applicationContext.getBeansOfType(WebServer.class);
    if (webServers.isEmpty()) {
      throw new NullPointerException("没有找到WebServer");
    } else if (webServers.size() > 1) {
      throw new IllegalStateException("找到多个WebServer");
    } else {
      return applicationContext.getBean(WebServer.class);
    }
  }

  public static void run(Class<?> clazz, String[] args) {
    throw new UnsupportedOperationException();
  }
}
