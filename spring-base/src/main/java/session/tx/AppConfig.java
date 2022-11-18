package session.tx;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("session.tx")
@Configuration
@EnableTransactionManagement
public class AppConfig {

  @Bean
  @Primary
  public DataSource mysqlDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
    dataSource.setUsername("root");
    dataSource.setPassword("ZiF6OZ5eB4gW2kN0");
    return dataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource mysqlDataSource) {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(mysqlDataSource);
    return transactionManager;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource mysqlDataSource) {
    return new JdbcTemplate(mysqlDataSource);
  }
}
