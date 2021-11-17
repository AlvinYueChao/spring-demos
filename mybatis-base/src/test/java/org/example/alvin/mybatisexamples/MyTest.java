package org.example.alvin.mybatisexamples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.alvin.mybatisexamples.mapper.CommonMapper;
import org.example.alvin.mybatisexamples.pojo.ConsultContractCardInfo;
import org.example.alvin.mybatisexamples.util.DataSourceUtils;
import org.junit.jupiter.api.Test;

public class MyTest {

  private final Logger logger = LogManager.getLogger(MyTest.class);

  @Test
  void test1() {
    SqlSession sqlSession = DataSourceUtils.getSqlSession();
    if (sqlSession != null) {
      CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
      List<ConsultContractCardInfo> result = commonMapper.queryContractByCardId();
      logger.info(result.size());
    }
  }

  @Test
  void test2() {
    SqlSession sqlSession = DataSourceUtils.getSqlSession();
    if (sqlSession != null) {
      CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
      Map<String, String> params = new HashMap<>();
      params.put("psptId", "456979432");
      List<Map<?, ?>> result = commonMapper.queryUserByPsptId(params);
      logger.info(result);
    }
  }
}
