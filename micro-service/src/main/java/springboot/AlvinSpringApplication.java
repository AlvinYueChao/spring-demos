package springboot;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import springboot.web.server.WebServer;

@Slf4j
public class AlvinSpringApplication {

  public static void run(Class<?> clazz) {
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.register(clazz);
    applicationContext.refresh();

    WebServer webServer = getWebServer(applicationContext);
    webServer.start();
  }

  private static WebServer getWebServer(WebApplicationContext applicationContext) {
    // 判断依赖的是Tomcat还是Netty
    return applicationContext.getBean(WebServer.class);
  }

  private static void startTomcat(WebApplicationContext applicationContext) {
    Tomcat tomcat = new Tomcat();

    Server server = tomcat.getServer();
    Service service = server.findService("Tomcat");

    Connector connector = new Connector();
    connector.setPort(8081);

    StandardEngine engine = new StandardEngine();
    engine.setDefaultHost("localhost");

    StandardHost host = new StandardHost();
    host.setName("localhost");

    String contextPath = "";
    StandardContext context = new StandardContext();
    context.setPath(contextPath);
    context.addLifecycleListener(new Tomcat.FixContextListener());

    host.addChild(context);
    engine.addChild(host);

    service.setContainer(engine);
    service.addConnector(connector);

    tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet(applicationContext));
    context.addServletMappingDecoded("/*", "dispatcher");

    try {
      tomcat.start();
    } catch (LifecycleException e) {
      log.error("Tomcat startup failed.", e);
    }
  }

  public static void run(Class<?> clazz, String[] args) {
    throw new UnsupportedOperationException();
  }
}
