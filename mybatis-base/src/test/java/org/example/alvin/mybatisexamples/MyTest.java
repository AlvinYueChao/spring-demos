package org.example.alvin.mybatisexamples;

import org.apache.ibatis.session.SqlSession;
import org.example.alvin.mybatisexamples.util.DataSourceUtils;
import org.junit.jupiter.api.Test;

public class MyTest {

  @Test
  void test1() {
    SqlSession sqlSession = DataSourceUtils.getSqlSession();
  }
}
