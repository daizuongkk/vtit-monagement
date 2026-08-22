package com.daizuongkk.monagement.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.dto.response.UserResponse;
import com.daizuongkk.monagement.entity.User;
import com.daizuongkk.monagement.exception.AppException;
import com.daizuongkk.monagement.exception.ErrorCode;
import com.daizuongkk.monagement.mapper.UserMapper;
import com.daizuongkk.monagement.repository.UserRepository;
import com.daizuongkk.monagement.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  public UserResponse create(RegisterRequest registerRequest) {

    if (userRepository.existsByUsername(registerRequest.getUsername())) {
      throw new AppException(ErrorCode.USERNAME_EXISTED);
    }

    User user = User.builder()
        .username(registerRequest.getUsername())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .build();

    userRepository.save(user);
    return userMapper.toUserResponse(user);
  }
}
