package springboot.web.server;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TomcatWebServer implements WebServer {

  @Override
  public void start() {
    log.info("启动Tomcat...");
  }
}
