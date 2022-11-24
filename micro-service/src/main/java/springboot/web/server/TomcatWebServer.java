package springboot.web.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Slf4j
public class TomcatWebServer implements WebServer, ApplicationContextAware {

  private WebApplicationContext applicationContext;

  @Override
  public void start() {
    log.info("启动Tomcat...");
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

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (applicationContext instanceof WebApplicationContext) {
      this.applicationContext = (WebApplicationContext) applicationContext;
    }
  }
}
