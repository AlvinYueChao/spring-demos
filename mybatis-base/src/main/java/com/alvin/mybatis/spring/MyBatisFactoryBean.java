package com.alvin.mybatis.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

@Slf4j
public class MyBatisFactoryBean implements FactoryBean<Object> {

  private final Class<?> mapperInterface;
  private SqlSession sqlSession;

  public MyBatisFactoryBean(Class<?> mapperInterface) {
    this.mapperInterface = mapperInterface;
  }

//  @Autowired
  public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
    sqlSessionFactory.getConfiguration().addMapper(this.mapperInterface);
    this.sqlSession = sqlSessionFactory.openSession();
  }

  @Override
  public Object getObject() throws Exception {
    /*return Proxy.newProxyInstance(MyBatisFactoryBean.class.getClassLoader(), new Class[]{this.mapperInterface}, (proxy, method, args) -> {
      log.info("{}", method.getName());
      return null;
    });*/
    return this.sqlSession.getMapper(this.mapperInterface);
  }

  @Override
  public Class<?> getObjectType() {
    return this.mapperInterface;
  }
}
