package org.example.alvin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.alvin.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
//@Service
public class RedisUserService implements UserService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    return User.withUsername("admin").password(encoder.encode("admin123")).roles("USER").build();
  }

  // Generate a function to load user by age
}
