package com.alvin;

import com.alvin.mybatis.spring.MyBatisMapperScan;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.alvin")
@MyBatisMapperScan("com.alvin.mapper")
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
}
