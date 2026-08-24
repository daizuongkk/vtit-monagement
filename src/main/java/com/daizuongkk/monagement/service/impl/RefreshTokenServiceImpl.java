package com.daizuongkk.monagement.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.entity.RefreshToken;
import com.daizuongkk.monagement.entity.User;
import com.daizuongkk.monagement.repository.RefreshTokenRepository;
import com.daizuongkk.monagement.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;

  @Value("${jwt.refresh-token.ttl}")
  private Long refreshTokenTTL;

  @Override
  public RefreshToken create(User user) {

    RefreshToken refreshToken = RefreshToken.builder()
        .value(UUID.randomUUID().toString())
        .userId(user.getId())
        .expiresIn(refreshTokenTTL)
        .build();

    return refreshTokenRepository.save(refreshToken);

  }

}
