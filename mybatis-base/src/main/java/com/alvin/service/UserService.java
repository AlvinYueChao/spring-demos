package com.alvin.service;

import com.alvin.mapper.OrderMapper;
import com.alvin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private OrderMapper orderMapper;

  @Transactional
  public void test() {
    /**
     * spring和mybatis整合，如果不开启spring事务，mybatis一级缓存会失效
     * 开启事务时，TransactionSynchronizationManager.isSynchronizationActive()返回值为true，否则为false
     */
    log.info("{}", userMapper.selectUserById());
    log.info("{}", orderMapper.selectOrderById());
  }
}
