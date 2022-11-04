package com.alvin.service;

import com.alvin.mapper.OrderMapper;
import com.alvin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private OrderMapper orderMapper;

  public void test() {
    log.info("{}", userMapper.selectUserById());
    log.info("{}", orderMapper.selectOrderById());
  }
}
