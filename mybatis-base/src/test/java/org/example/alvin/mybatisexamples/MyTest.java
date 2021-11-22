package org.example.alvin.mybatisexamples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.alvin.mybatisexamples.mapper.CommonMapper;
import org.example.alvin.mybatisexamples.pojo.ConsultContractCardInfo;
import org.example.alvin.mybatisexamples.pojo.ConsultRecordCount;
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

  @Test
  void test3() {
    SqlSession sqlSession = DataSourceUtils.getSqlSession();
    if (sqlSession != null) {
      CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
      Map<String, String> params = new HashMap<>();
      List<ConsultRecordCount> result = commonMapper.queryRecordCount(params);
      logger.info(result);
    }
  }

  @Test
  void test4() {
    SqlSession sqlSession = DataSourceUtils.getSqlSession();
    if (sqlSession != null) {
      CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
      Map<String, String> params = new HashMap<>();
      params.put("psptId", "456979432");
      List<ConsultRecordCount> result = commonMapper.queryRecordCount(params);
      logger.info(result);
    }
  }

  @Test
  void test5() {
    // Mybatis ��Ƕ�ײ�ѯ��һ����ѯ���û������ͨ�� collection ������һ����ѯ���
    SqlSession sqlSession = DataSourceUtils.getSqlSession();
    if (sqlSession != null) {
      CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
      Map<String, String> params = new HashMap<>();
      List<ConsultContractCardInfo> result = commonMapper.getTop4Contracts(params);
      for (ConsultContractCardInfo consultContractCardInfo : result) {
        logger.info("result from consult_contract: {}", consultContractCardInfo);
      }
    }
  }

  @Test
  void test6() {
    // Mybatis Ƕ�ײ�ѯ + ������
    SqlSession sqlSession = DataSourceUtils.getSqlSession();
    if (sqlSession != null) {
      CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
      Map<String, String> params = new HashMap<>();
      List<ConsultContractCardInfo> result = commonMapper.getTop4ContractsLazy(params);
      for (ConsultContractCardInfo consultContractCardInfo : result) {
        logger.info("ConsultContractCardInfo[contractId={}, contractCode={}, activeTime={}, state={}]", consultContractCardInfo.getContractId(),
            consultContractCardInfo.getContractCode(), consultContractCardInfo.getActiveTime(), consultContractCardInfo.getState());
      }
      for (ConsultContractCardInfo consultContractCardInfo : result) {
        logger.info("ConsultContractCardInfo[contractId={}, infos={}]", consultContractCardInfo.getContractId(), consultContractCardInfo.getInfos());
      }
    }
  }
}
