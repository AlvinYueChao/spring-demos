package org.example.alvin.springexamples.annotation.aop.transaction;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "application.properties")
public class DatabaseConfig {

  @Value("${datasource.url}")
  private String url;

  @Value("${datasource.username}")
  private String username;

  @Value("${datasource.password}")
  private String password;

  @Bean
  protected DataSource mysqlDataSource() {
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL(this.url);
    mysqlDataSource.setUser(this.username);
    mysqlDataSource.setPassword(this.password);
    return mysqlDataSource;
  }
}
