package org.example.alvin.mybatisexamples.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSourceUtils {

  private static final Logger logger = LogManager.getLogger();

  private DataSourceUtils() {
  }

  public static SqlSession getSqlSession() {
    String resourceFileName = "mybatis-config.xml";
    SqlSessionFactory sqlSessionFactory = null;
    try {
      InputStream inputStream = Resources.getResourceAsStream(resourceFileName);
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      sqlSessionFactory = builder.build(inputStream);
    } catch (IOException e) {
      logger.warn("Cannot find the resource file!", e);
    }
    return sqlSessionFactory != null ? sqlSessionFactory.openSession() : null;
  }
}
