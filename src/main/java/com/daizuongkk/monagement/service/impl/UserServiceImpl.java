package com.daizuongkk.monagement.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.dto.response.UserResponse;
import com.daizuongkk.monagement.mapper.UserMapper;
import com.daizuongkk.monagement.repository.UserRepository;
import com.daizuongkk.monagement.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  @Override
  @Transactional
  public UserResponse create(RegisterRequest registerRequest) {

    return UserResponse.builder().build();

  }
}
