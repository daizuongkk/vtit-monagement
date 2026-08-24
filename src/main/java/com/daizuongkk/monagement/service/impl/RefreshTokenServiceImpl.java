package com.daizuongkk.monagement.service.impl;

import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.entity.RefreshToken;
import com.daizuongkk.monagement.entity.User;
import com.daizuongkk.monagement.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl {

  public final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken create(User user) {

    RefreshToken refreshToken = RefreshToken.builder()
        .userId(user.getId())
        .expiresIn(null)
        .build();

    return refreshTokenRepository.save(refreshToken);

  }

}
