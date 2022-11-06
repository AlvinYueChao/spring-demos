package com.alvin;

import com.alvin.mybatis.spring.MyBatisMapperScan;
import java.io.IOException;
import java.io.InputStream;
import javax.sql.DataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.alvin")
//@MyBatisMapperScan("com.alvin.mapper")
@MapperScan("com.alvin.mapper")
@EnableTransactionManagement
public class AppConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactory() {
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream("mybatis.xml");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return new SqlSessionFactoryBuilder().build(inputStream);
  }

  @Bean
  @Primary
  public DataSource mysqlDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/consult");
    dataSource.setUsername("root");
    dataSource.setPassword("ZiF6OZ5eB4gW2kN0");
    return dataSource;
  }

  @Bean
  public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
