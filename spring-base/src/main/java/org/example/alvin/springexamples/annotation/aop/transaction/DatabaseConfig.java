package org.example.alvin.springexamples.annotation.aop.transaction;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
@PropertySource(value = "application.properties")
public class DatabaseConfig {

  private final Logger logger = LogManager.getLogger(DatabaseConfig.class);

  @Value("${datasource.driverClassName}")
  private String driverClassName;

  @Value("${datasource.url}")
  private String url;

  @Value("${datasource.username}")
  private String username;

  @Value("${datasource.password}")
  private String password;

  @Bean
  protected DataSource comboPooledDataSource() {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    try {
      comboPooledDataSource.setDriverClass(driverClassName);
      comboPooledDataSource.setJdbcUrl(url);
      comboPooledDataSource.setUser(username);
      comboPooledDataSource.setPassword(password);
      comboPooledDataSource.setMinPoolSize(10);
      comboPooledDataSource.setMaxPoolSize(100);
      comboPooledDataSource.setAcquireIncrement(3);
      comboPooledDataSource.setMaxStatements(1000);
      comboPooledDataSource.setInitialPoolSize(10);
      comboPooledDataSource.setIdleConnectionTestPeriod(60);
      comboPooledDataSource.setAcquireRetryAttempts(30);
      comboPooledDataSource.setBreakAfterAcquireFailure(false);
      comboPooledDataSource.setTestConnectionOnCheckout(false);
      comboPooledDataSource.setAcquireRetryDelay(100);
    } catch (PropertyVetoException e) {
      logger.error("Cannot find the available properties", e);
    }
    return comboPooledDataSource;
  }

  @Bean
  public PlatformTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
    DataSourceTransactionManager dtm = new DataSourceTransactionManager();
    dtm.setDataSource(dataSource);
    return dtm;
  }
}
