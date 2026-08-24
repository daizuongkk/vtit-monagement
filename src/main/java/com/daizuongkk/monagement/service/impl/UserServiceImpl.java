package com.daizuongkk.monagement.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.entity.Role;
import com.daizuongkk.monagement.entity.User;
import com.daizuongkk.monagement.repository.UserRepository;
import com.daizuongkk.monagement.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public User create(RegisterRequest registerRequest) {

    User user = User.builder()
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .role(Role.USER)
        .build();

    return userRepository.save(user);

  }
}
