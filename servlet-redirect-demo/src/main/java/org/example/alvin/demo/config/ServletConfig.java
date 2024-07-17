package org.example.alvin.demo.config;

import org.example.alvin.demo.web.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

  @Bean
  public ServletRegistrationBean<MyServlet> servletRegistrationBean() {
    ServletRegistrationBean<MyServlet> servletRegistrationBean = new ServletRegistrationBean<>(new MyServlet(), "/api/*");
    servletRegistrationBean.setEnabled(true);
    servletRegistrationBean.setLoadOnStartup(Integer.MAX_VALUE);
    return servletRegistrationBean;
  }
}
