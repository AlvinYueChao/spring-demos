package springboot.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.web.condition.ConditionalOnWebServerClass;
import springboot.web.server.NettyWebServer;
import springboot.web.server.TomcatWebServer;

@Configuration
public class WebServerAutoConfiguration {

  @Bean
  @ConditionalOnWebServerClass("org.apache.catalina.startup.Tomcat")
  public TomcatWebServer tomcatWebServer() {
    return new TomcatWebServer();
  }

  @Bean
  @ConditionalOnWebServerClass("")
  public NettyWebServer nettyWebServer() {
    return new NettyWebServer();
  }
}
